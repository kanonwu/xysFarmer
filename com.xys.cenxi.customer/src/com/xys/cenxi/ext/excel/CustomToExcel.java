package com.xys.cenxi.ext.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xys.cenxi.customer.data.FamilyService;
import com.xys.cenxi.customer.exception.CusException;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Family;
import com.xys.cenxi.customer.util.Util;

public class CustomToExcel {
	
	private static Logger logger = LoggerFactory.getLogger(CustomToExcel.class);
	
	private Customer customer;
	
	private Workbook readWorkBook;
	
	private WritableWorkbook outWorkBook;
	
	private Sheet readSheet;
	
	private WritableSheet outSheet;
	
	private OutputStream os;
	
	private static final String OriginalExcel = "originalExcel.xls";
	
	public CustomToExcel(String outPath){
		File outFile = new File(outPath);
		try {
			os = new FileOutputStream(outFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("�ļ�������:", e);
			throw new CusException(e);
		}
	}
	
	public void writeExcel(Customer cus){
		readExcel(OriginalExcel);
		this.customer = cus;
		try {
			writeHead();
		} catch (Exception e) {
			logger.error("���ش���", e);
			throw new CusException(e);
		}finally{
			try {
				outWorkBook.write();
				outWorkBook.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("�ر��ļ�����", e);
			} catch (WriteException e) {
				e.printStackTrace();
				logger.error("д��excel����", e);
			}
			
		}
		
	}
	
	private void readExcel(String filePath){
		try {
			readWorkBook = Workbook.getWorkbook(new File(filePath));
			outWorkBook = Workbook.createWorkbook(os, readWorkBook);
		} catch (BiffException e) {
			logger.error("��ȡexcel����", e);
			throw new CusException(e);
		} catch (IOException e) {
			logger.error("��ȡexcel����", e);
			throw new CusException(e);
		}
		readSheet = readWorkBook.getSheet(0);
	}
	
	private void writeHead() throws Exception{
		outSheet = outWorkBook.getSheet(0);
		// �������
		WritableCell cellArchive = outSheet.getWritableCell("X1");
		if(cellArchive.getType() == CellType.LABEL){
			Label label = (Label) cellArchive;
			label.setString(label.getString() + customer.getArchivesID());
		}
		
		//��������
		WritableCell cellName = outSheet.getWritableCell("B3");
		if(cellName.getType() == CellType.LABEL){
			Label lblName = (Label) cellName;
			lblName.setString(customer.getName());
		}else{
			Label lblName = new Label(cellName.getColumn(), cellName.getRow(), customer.getName());
			outSheet.addCell(lblName);
		}
		
		//��ϵ�绰
		WritableCell cellTell = outSheet.getWritableCell("O3");
		if(cellTell.getType() == CellType.LABEL){
			Label lblTell = (Label) cellTell;
			lblTell.setString(customer.getTelPhone());
		}else{
			Label lblTell = new Label(cellTell.getColumn(), cellTell.getRow(), customer.getTelPhone());
			outSheet.addCell(lblTell);
		}

		//�ֻ�����
		WritableCell cellPhone = outSheet.getWritableCell("X3");
		if(cellPhone.getType() == CellType.LABEL){
			Label lblPhone = (Label) cellPhone;
			lblPhone.setString(customer.getMobilePhone());
		}else{
			Label lblPhone = new Label(cellPhone.getColumn(), cellPhone.getRow(), customer.getMobilePhone());
			outSheet.addCell(lblPhone);
		}

		//ͨ�ŵ�ַ
		WritableCell cellAddress = outSheet.getWritableCell("B4");
		if(cellAddress.getType() == CellType.LABEL){
			Label lblAdd = (Label) cellAddress;
			lblAdd.setString(customer.getAddress());
		}else{
			Label lblAdd = new Label(cellAddress.getColumn(), cellAddress.getRow(), customer.getAddress());
			outSheet.addCell(lblAdd);
		}
		
		//��������
		WritableCell cellPostCode = outSheet.getWritableCell("X4");
		if(cellPostCode.getType() == CellType.LABEL){
			Label lblPostCode = (Label) cellPostCode;
			lblPostCode.setString(customer.getPostCode());
		}else{
			Label lblPostCode = new Label(cellPostCode.getColumn(), cellPostCode.getRow(), customer.getPostCode());
			outSheet.addCell(lblPostCode);
		}
		
		//��ͥ��Ա
		//�ȼ��뻧��
		addFamilyMember(customer);
		
		//������ͥ��Ա
		List<Family> familys = FamilyService.getInstance().getFamily(customer.getRowID());
		int faRow = 7;
		for(Family fa : familys){
			addFamilyMember(fa, faRow);
			faRow++;
			if(faRow > 11){
				outSheet.insertRow(faRow);
			}
		}
	}
	
	private void addFamilyMember(Family fa, int row) throws Exception{
		//����
		WritableCell cusCell = outSheet.getWritableCell(0, row);
		if(!Util.isEmpty(fa.getName())){
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(fa.getName());
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), fa.getName());
				outSheet.addCell(label);
			}
		}
		
		//�뻧����ϵ
		if(Util.isEmpty(fa.getRelation())){
			cusCell = outSheet.getWritableCell(1, row);
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(fa.getRelation());
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), fa.getRelation());
				outSheet.addCell(label);
			}
		}
		
		//���֤
		String idStr = fa.getIdentify();
		if(!Util.isEmpty(idStr)){
			int col = 2;
			for(int i = 0; i < idStr.length(); i++){
				String no = String.valueOf(idStr.charAt(i));
				cusCell = outSheet.getWritableCell(col, row);
				if(cusCell.getType() == CellType.LABEL){
					Label label = (Label) cusCell;
					label.setString(no);
				}else{
					Label label = new Label(cusCell.getColumn(), cusCell.getRow(), no);
					outSheet.addCell(label);
				}
				col++;
			}
		}
		
		//�Ա�
		if(Util.isEmpty(customer.getGender())){
			cusCell = outSheet.getWritableCell(20, row);
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(fa.getGender());
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), fa.getGender());
				outSheet.addCell(label);
			}
		}

		//��������
		if(fa.getBirthday() != null){
			cusCell = outSheet.getWritableCell(21, row);
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(Util.DATE_SDF.format(fa.getBirthday()));
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), Util.DATE_SDF.format(fa.getBirthday()));
				outSheet.addCell(label);
			}
		}

		//�Ļ��̶�
		if(!Util.isEmpty(fa.getEducation())){
			cusCell = outSheet.getWritableCell(23, row);
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(fa.getEducation());
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), fa.getEducation());
				outSheet.addCell(label);
			}
		}
		
		//����״��
		if(!Util.isEmpty(fa.getMarry())){
			cusCell = outSheet.getWritableCell(24, row);
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(fa.getMarry());
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), fa.getMarry());
				outSheet.addCell(label);
			}
		}

		
		//��ϵ�绰
		String phone = fa.getPhone();
		if(!Util.isEmpty(phone)){
			cusCell = outSheet.getWritableCell(25, row);
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(phone);
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), phone);
				outSheet.addCell(label);
			}
		}

	}
	
	private void addFamilyMember(Customer cus) throws Exception{
		//����
		WritableCell cusCell = outSheet.getWritableCell("A7");
		if(!Util.isEmpty(cus.getName())){
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(cus.getName());
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), cus.getName());
				outSheet.addCell(label);
			}
		}
		//�뻧����ϵ
		cusCell = outSheet.getWritableCell("B7");
		if(cusCell.getType() == CellType.LABEL){
			Label label = (Label) cusCell;
			label.setString("����");
		}else{
			Label label = new Label(cusCell.getColumn(), cusCell.getRow(), "����");
			outSheet.addCell(label);
		}
		//���֤
		String idStr = cus.getIdentify();
		if(!Util.isEmpty(idStr)){
			int col = 2;
			int row = 6;
			for(int i = 0; i < idStr.length(); i++){
				String no = String.valueOf(idStr.charAt(i));
				cusCell = outSheet.getWritableCell(col, row);
				if(cusCell.getType() == CellType.LABEL){
					Label label = (Label) cusCell;
					label.setString(no);
				}else{
					Label label = new Label(cusCell.getColumn(), cusCell.getRow(), no);
					outSheet.addCell(label);
				}
				col++;
			}
		}
		
		//�Ա�
		if(Util.isEmpty(customer.getGender())){
			cusCell = outSheet.getWritableCell("U7");
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(cus.getGender());
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), cus.getGender());
				outSheet.addCell(label);
			}
		}

		//��������
		if(cus.getBirthday() != null){
			cusCell = outSheet.getWritableCell("V7");
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(Util.DATE_SDF.format(cus.getBirthday()));
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), Util.DATE_SDF.format(cus.getBirthday()));
				outSheet.addCell(label);
			}
		}

		//�Ļ��̶�
		if(!Util.isEmpty(cus.getEducation())){
			cusCell = outSheet.getWritableCell("X7");
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(cus.getEducation());
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), cus.getEducation());
				outSheet.addCell(label);
			}
		}
		
		//����״��
		if(!Util.isEmpty(cus.getMarry())){
			cusCell = outSheet.getWritableCell("Y7");
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(cus.getMarry());
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), cus.getMarry());
				outSheet.addCell(label);
			}
		}

		
		//��ϵ�绰
		String phone = cus.getMobilePhone();
		if(Util.isEmpty(phone)){
			phone = cus.getTelPhone();
		}
		if(!Util.isEmpty(phone)){
			cusCell = outSheet.getWritableCell("Z7");
			if(cusCell.getType() == CellType.LABEL){
				Label label = (Label) cusCell;
				label.setString(phone);
			}else{
				Label label = new Label(cusCell.getColumn(), cusCell.getRow(), phone);
				outSheet.addCell(label);
			}
		}
	}

}
