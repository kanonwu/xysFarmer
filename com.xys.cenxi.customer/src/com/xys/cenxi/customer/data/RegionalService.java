package com.xys.cenxi.customer.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.Regional;
import com.xys.cenxi.customer.util.OrderGenerator;
import com.xys.cenxi.customer.util.Util;

public class RegionalService {

	private static Logger logger = LoggerFactory
			.getLogger(RegionalService.class);

	private static RegionalService service;

	private RegionalService() {

	}
	
	public Regional getRegionalByCode(String code){
		Dao dao = DataSourceManager.getDao();
		return dao.fetch(Regional.class, Cnd.where("regionalCode", "=", code));
	}

	public static RegionalService getInstance() {
		if (service == null) {
			service = new RegionalService();
		}

		return service;
	}

	public Regional getRegional(String rowID) {
		Dao dao = DataSourceManager.getDao();
		return dao.fetch(Regional.class, Cnd.where("rowID", "=", rowID));
	}

	public boolean hasChildren(Regional re) {
		Dao dao = DataSourceManager.getDao();
		Regional children = dao.fetch(Regional.class,
				Cnd.where("parentID", "=", re.getRowID()));
		return children != null;
	}

	public List<Regional> getRegionalByParentID(String parentID) {
		Dao dao = DataSourceManager.getDao();
		Criteria cri = Cnd.cri();
		if (parentID == null) {
			cri.where().andIsNull("parentID");
		} else {
			cri.where().andEquals("parentID", parentID);
		}

		return dao.query(Regional.class, cri);
	}

	public Regional add(Regional re) {
		Dao dao = DataSourceManager.getDao();
		if (re.getRowID() == null) {
			re.setRowID(OrderGenerator.newOrder());
		}
		return dao.insert(re);
	}

	public void loadRegionalFromFile(File file) throws Exception {
		Workbook excel = Workbook.getWorkbook(file);
		Sheet sheet = excel.getSheet(0);
		if (sheet == null) {
			logger.warn("Excel文件没有数据。");
		}
		Regional cenxi = new Regional();
		cenxi.setName("岑溪市");
		cenxi.setRegionalCode("45048100000000");
		cenxi.setSequence("00");
		add(cenxi);

		List<Regional> towns = new ArrayList<Regional>();
		List<Integer> townIndex = new ArrayList<Integer>();
		int rows = sheet.getRows();
		for (int row = 2; row < rows; row++) {
			// 镇
			Cell cell = sheet.getCell(3, row);
			String name = cell.getContents();
			if (Util.isEmpty(name)) {
				continue;
			}

			String code = sheet.getCell(7, row).getContents();
			// 行政区划代码是空的，结束。
			if (Util.isEmpty(code)) {
				break;
			}

			// 找到名称
			Regional reTown = new Regional();
			reTown.setName(name);
			Cell cellSequence = sheet.getCell(6, row);
			reTown.setSequence(cellSequence.getContents());
			reTown.setRegionalCode(code);
			reTown.setDesc(sheet.getCell(8, row).getContents());
			reTown.setParentID(cenxi.getRowID());
			add(reTown);

			towns.add(reTown);
			townIndex.add(row);
		}

		// 村
		for (int t = 0, s = townIndex.size(); t < s; t++) {
			int begin = townIndex.get(t);
			int end = rows;
			if (t < s - 1) {
				end = townIndex.get(t + 1);
			}

			Regional reTown = towns.get(t);
			
			for(int villageRow = begin + 1; villageRow < end; villageRow++){
				Cell cell = sheet.getCell(4, villageRow);
				String name = cell.getContents();
				if (Util.isEmpty(name)) {
					continue;
				}
				
				String code = sheet.getCell(7, villageRow).getContents();
				// 行政区划代码是空的，结束。
				if (Util.isEmpty(code)) {
					break;
				}
				
				// 找到名称
				Regional reVillage = new Regional();
				reVillage.setName(name);
				Cell cellSequence = sheet.getCell(6, villageRow);
				reVillage.setSequence(cellSequence.getContents());
				reVillage.setRegionalCode(code);
				reVillage.setDesc(sheet.getCell(8, villageRow).getContents());
				reVillage.setParentID(reTown.getRowID());
				add(reVillage);
				
				// 组
				int rowZu = villageRow + 1;
				Cell cellZu = sheet.getCell(5, rowZu);
				String nameZu = cellZu.getContents();
				while (!Util.isEmpty(nameZu)) {
					code = sheet.getCell(7, rowZu).getContents();
					// 行政区划代码是空的，结束。
					if (Util.isEmpty(code)) {
						break;
					}
					
					Regional reZU = new Regional();
					reZU.setName(nameZu);
					Cell cellSequenceZu = sheet.getCell(6, rowZu);
					reZU.setSequence(cellSequenceZu.getContents());
					reZU.setRegionalCode(code);
					reZU.setDesc(sheet.getCell(8, rowZu).getContents());
					reZU.setParentID(reVillage.getRowID());
					add(reZU);
					
					rowZu++;
					cellZu = sheet.getCell(5, rowZu);
					nameZu = cellZu.getContents();
				}
			}
		}

		excel.close();
	}
}
