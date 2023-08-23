
package com.schoollazypkg;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import org.primefaces.model.SortOrder;
import com.schoolmodel.UpdateSchoolModel;
import com.schoolserviceimp.UpdateSchoolServiceImp;

public class UpdateSchoolLazy extends LazyDataModel<UpdateSchoolModel> {
	
	private UpdateSchoolServiceImp schoolService;
	private int rowCount;
	
	public UpdateSchoolLazy(UpdateSchoolServiceImp schoolService) {
        this.schoolService = schoolService;
    }	
	
	@Override
    public List<UpdateSchoolModel> load(int first, int pageSize, String sortField,
                               SortOrder sortOrder, Map<String, Object> filters) {

        List<UpdateSchoolModel> data = schoolService.getSchoolList(first,pageSize,sortField,sortOrder,filters);
        rowCount = schoolService.getFilteredRowCount(filters);
        this.setRowCount(rowCount);
        
        return data;
    }

	public int getRowCount() {
		return rowCount;
	}	

}

