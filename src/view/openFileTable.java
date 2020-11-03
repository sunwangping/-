package view;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import fileOperation.fileOperation;
import model.openFile;

public class openFileTable extends AbstractTableModel{

	private Vector<String> columnNames;
	private Vector<Vector<String>> rowDatas;
	private fileOperation fileOperation;
	
	public openFileTable() {
		fileOperation = new fileOperation();
		initData();
	}
	
	public void initData() {
		columnNames = new Vector<String>();
		columnNames.add("文件名称");
		columnNames.add("文件属性");
		columnNames.add("文件起始盘块号");
		columnNames.add("文件占用空间大小");
		columnNames.add("文件占用盘块数");
		columnNames.add("文件路径");
		
		Vector<String> vc = null;
		rowDatas = new Vector<Vector<String>>();
		openFile openFile = fileOperation.getOpenFile();
		for (int i=0; i<5; i++){
			vc = new Vector<String>();
			if(i < openFile.getFilesList().size()){
				vc.add(openFile.getFilesList().get(i).getFile().getFileName());
				vc.add(openFile.getFilesList().get(i).getFile().isReadOnly() == true? "只读" : "读写");
				vc.add(openFile.getFilesList().get(i).getFile().getStartDiskNum() + "");
				vc.add(openFile.getFilesList().get(i).getFile().getSize() + "");
				vc.add(openFile.getFilesList().get(i).getFile().getNumInFAT() + "");
				vc.add(openFile.getFilesList().get(i).getFile().getpath());
			} else {
				vc.add("");
				vc.add("");
				vc.add("");
				vc.add("");
				vc.add("");
				vc.add("");
			}
			rowDatas.add(vc);
		}
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return rowDatas.get(rowIndex).get(columnIndex);
	}
	
}
