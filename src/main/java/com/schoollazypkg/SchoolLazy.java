package com.schoollazypkg;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import org.primefaces.model.SortOrder;
import com.schoolmodel.SchoolModel;
import com.schoolserviceimp.SchoolServiceImp;

public class SchoolLazy extends LazyDataModel<SchoolModel> {
	
	private SchoolServiceImp schoolService;
	private int rowCount;
	
	public SchoolLazy(SchoolServiceImp schoolService) {
        this.schoolService = schoolService;
    }
	
	
	
	
	
	@Override
    public List<SchoolModel> load(int first, int pageSize, String sortField,
                               SortOrder sortOrder, Map<String, Object> filters) {

        List<SchoolModel> data = schoolService.getSchoolList(first,pageSize,sortField,sortOrder,filters);
        
 //       int dataSize = schoolService.getTotalSchoolList();
        rowCount = schoolService.getFilteredRowCount(filters);
        this.setRowCount(rowCount);
        
        return data;
    }





	public int getRowCount() {
		return rowCount;
	}





	
	
	
	
	
	
	

}
