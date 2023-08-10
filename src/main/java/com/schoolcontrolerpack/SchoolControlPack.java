package com.schoolcontrolerpack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

import com.schoolmodel.SchoolModel;
import com.schoolserviceimp.SchoolServiceImp;

import com.schoollazypkg.SchoolLazy;

@ManagedBean(name = "sc")
@ViewScoped
public class SchoolControlPack implements Serializable {

    private List<SchoolModel> schools;
    private SchoolModel school;
    private SchoolModel newSchool;
    private SchoolModel updatedSchool;
    private SchoolModel schoolEdit;
    private boolean editDisabled = false;
    
    
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;
	
	private LazyDataModel<SchoolModel> lazySchoolModel;
	
//	private List<SchoolModel> filteredSchools;
// 
//
//
//
//    public List<SchoolModel> getFilteredSchools() {
//		return filteredSchools;
//	}
//
//	public void setFilteredSchools(List<SchoolModel> filteredSchools) {
//		this.filteredSchools = filteredSchools;
//	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public UIForm getTableForm() {
		return tableForm;
	}

	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}

	public UICommand getAddCommand() {
		return addCommand;
	}

	public void setAddCommand(UICommand addCommand) {
		this.addCommand = addCommand;
	}

	@Inject
    private SchoolServiceImp schoolService;

    public SchoolControlPack() {
        schools = new ArrayList<SchoolModel>();
        newSchool = new SchoolModel(); 
        updatedSchool = new SchoolModel();
    }

    @PostConstruct
    public void init() {
    	 lazySchoolModel = new SchoolLazy(schoolService);
    	 
    	 
    	   	
//      schools = schoolService.findAll();
//     newSchool= new SchoolModel();
    }

    public List<SchoolModel> addNew() {
        schoolService.createSchool(newSchool);
        schools = schoolService.findAll();
        newSchool = new SchoolModel();
       
        return schools;
    }
    

    
    public void delete() {
        schoolService.deleteSchool(school);
        schools = schoolService.findAll();
    }
    
    
 //  without primeFaces update code
    
//    public List<SchoolModel> updateSchool() {
//        schoolService.updateSchool(updatedSchool);
//        schools = schoolService.findAll();
//        updatedSchool = new SchoolModel();
//        setEditDisabled(false);
//		school.setEdit(false);
//        return schools;
//    }
    
    public void updateSchool(RowEditEvent event) {
        SchoolModel editedSchool = (SchoolModel) event.getObject();
        schoolService.updateSchool(editedSchool);
        FacesMessage msg = new FacesMessage("School Edited", String.valueOf(editedSchool.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getClass()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    
    
    
    
    
    
    


    
    
    

    
    
    

    
    
 


   

    public List<SchoolModel> getSchools() {
//    	if (filteredSchools != null) {
//            return filteredSchools;
//        }
    	
        return schools;
    }

    public void setSchools(List<SchoolModel> schools) {
        this.schools = schools;
    }

    public SchoolModel getSchool() {
        return school;
    }

    public void setSchool(SchoolModel school) {
        this.school = school;
    }

    public SchoolModel getNewSchool() {
        return newSchool;
    }

    public void setNewSchool(SchoolModel newSchool) {
        this.newSchool = newSchool;
    }

    public SchoolServiceImp getSchoolService() {
        return schoolService;
    }

    public void setSchoolService(SchoolServiceImp schoolService) {
        this.schoolService = schoolService;
    }

	public SchoolModel getUpdatedSchool() {
		return updatedSchool;
	}

	public void setUpdatedSchool(SchoolModel updatedSchool) {
		this.updatedSchool = updatedSchool;
	}

	public SchoolModel getSchoolEdit() {
		return schoolEdit;
	}

	public void setSchoolEdit(SchoolModel schoolEdit) {
		this.schoolEdit = schoolEdit;
	}

	public boolean isEditDisabled() {
		return editDisabled;
	}

	public void setEditDisabled(boolean editDisabled) {
		this.editDisabled = editDisabled;
	}
	
	public LazyDataModel<SchoolModel> getLazySchoolModel() {
        return lazySchoolModel;
    }
	
	
    
    
//  public String edit() {
//  	setEditDisabled(true);
//		school.setEdit(true);
//		schoolEdit = new SchoolModel();
//		schoolEdit.setId(school.getId());
//		schoolEdit.setName(school.getName());
//		schoolEdit.setClassName(school.getClassName());
//		schoolEdit.setEdit(false);
//		schoolEdit.setMarks(school.getMarks());
//		return null;
//	}
  
  
  
  
//  public String editSave() {
//		setEditDisabled(false);
//		school.setEdit(false);
//		schoolService.editSchool(school);
//		schools = schoolService.findAll();
//	
//		return null;
//	}
  
  
  
  

  
  
//  public String editReset() {
//		setEditDisabled(false);
//		school.setEdit(false);
//		school.setId(schoolEdit.getId());
//		school.setName(schoolEdit.getName());
//		school.setClassName(schoolEdit.getClassName());
//		school.setMarks(schoolEdit.getMarks());
//		return null;
//	}
   




    
    

}
