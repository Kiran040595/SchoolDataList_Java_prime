package com.schoolcontrolerpack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

import com.schoolmodel.UpdateSchoolModel;
import com.schoolserviceimp.UpdateSchoolServiceImp;

import com.schoollazypkg.UpdateSchoolLazy;


import org.primefaces.model.DualListModel;

@ManagedBean(name = "scu")
@ViewScoped
public class UpdateSchoolControl implements Serializable {

    private List<UpdateSchoolModel> schools;
    private UpdateSchoolModel school;
    private UpdateSchoolModel newSchool;
    private UpdateSchoolModel updatedSchool;
    private UpdateSchoolModel schoolEdit;
    private boolean editDisabled = false;

    private UIForm form;
    private UIForm tableForm;
    private UICommand addCommand;

    private LazyDataModel<UpdateSchoolModel> lazySchoolModel;
    
    private DualListModel<String> selectedIdsDualListModel;

    private Map<String, Map<String, String>> data = new HashMap<>();
    private String district;
    private String city;
    private Map<String, String> districts;
    private Map<String, String> cities;
    
    

	

	List<String> sourceIdsList;
    List<String> targetIdsList;



    @Inject
    private UpdateSchoolServiceImp schoolService;

    public UpdateSchoolControl() {
        schools = new ArrayList<>();
        newSchool = new UpdateSchoolModel();
        updatedSchool = new UpdateSchoolModel();
        sourceIdsList = new ArrayList<>();
        targetIdsList = new ArrayList<>();
        
    }

    @PostConstruct
    public void init() {
        lazySchoolModel = new UpdateSchoolLazy(schoolService);
        
        selectedIdsDualListModel = new DualListModel<>(sourceIdsList,targetIdsList);
        
        List<UpdateSchoolModel> allSchool = schoolService.findAll();
        for(UpdateSchoolModel eachSchool: allSchool) {
        	sourceIdsList.add(eachSchool.getId());
        }

        districts = new HashMap<>();
        districts.put("Visakhapatnam", "Visakhapatnam");
        districts.put("Anakapalli", "Anakapalli");
        districts.put("Hyderabad", "Hyderabad");

        Map<String, String> map = new HashMap<>();
        map.put("Gajuwaka", "Gajuwaka");
        map.put("NAD Junction", "NAD Junction");
        map.put("Gurudwara", "Gurudwara");
        data.put("Visakhapatnam", map);

        map = new HashMap<>();
        map.put("Venkuaplem", "venkupalem");
        map.put("Tummapala", "Tummapala");
        map.put("Gavarapalem", "Gavarapalem");
        data.put("Anakapalli", map);

        map = new HashMap<>();
        map.put("Gachibowli", "Gachibowli");
        map.put("Shamshabad", "Shamshabad");
        map.put("Kukatpally", "Kukatpally");
        data.put("Hyderabad", map);
    }
    
    public List<UpdateSchoolModel> addNew() {
    	newSchool.setDistrict(district);
        newSchool.setCity(city);
        schoolService.createSchool(newSchool);
//      sourceIdsList.add(String.valueOf(newSchool.getId()));
        selectedIdsDualListModel.getSource().add(newSchool.getId());
        schools = schoolService.findAll();
        
        newSchool = new UpdateSchoolModel();
       district=null;
       city=null;
        return schools;
    }
    
    public void delete() {
        schoolService.deleteSchool(school);
        sourceIdsList.remove(school.getId());      
        schools = schoolService.findAll();
    }

    public void updateSchool(RowEditEvent event) {
    	
        UpdateSchoolModel editedSchool = (UpdateSchoolModel) event.getObject();
        editedSchool.setDistrict(district);
        editedSchool.setCity(city);
        schoolService.updateSchool(editedSchool);
        FacesMessage msg = new FacesMessage("School Edited", editedSchool.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getClass()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onDistrictChange() {
        if (district != null && !"".equals(district)) {
            cities = data.get(district);
        } else {
            cities = new HashMap<>();
        }
    }

    public void displayLocation() {
        FacesMessage msg;
        if (city != null && district != null) {
            msg = new FacesMessage("Selected", city + " of " + district);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }   
    
    
    
    public void deleteSelectedSchools() {
        List<String> selectedIds = selectedIdsDualListModel.getTarget();
        schoolService.deleteSchoolsByIds(selectedIds);
        selectedIdsDualListModel.getTarget().clear();  
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, String> getDistricts() {
        return districts;
    }

    public void setDistricts(Map<String, String> districts) {
        this.districts = districts;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void setCities(Map<String, String> cities) {
        this.cities = cities;
    }    

    public List<UpdateSchoolModel> getSchools() {
        return schools;
    }

    public void setSchools(List<UpdateSchoolModel> schools) {
        this.schools = schools;
    }

    public UpdateSchoolModel getSchool() {
        return school;
    }

    public void setSchool(UpdateSchoolModel school) {
        this.school = school;
    }

    public UpdateSchoolModel getNewSchool() {
        return newSchool;
    }

    public void setNewSchool(UpdateSchoolModel newSchool) {
        this.newSchool = newSchool;
    }

    public UpdateSchoolServiceImp getSchoolService() {
        return schoolService;
    }

    public void setSchoolService(UpdateSchoolServiceImp schoolService) {
        this.schoolService = schoolService;
    }

    public UpdateSchoolModel getUpdatedSchool() {
        return updatedSchool;
    }

    public void setUpdatedSchool(UpdateSchoolModel updatedSchool) {
        this.updatedSchool = updatedSchool;
    }

    public UpdateSchoolModel getSchoolEdit() {
        return schoolEdit;
    }

    public void setSchoolEdit(UpdateSchoolModel schoolEdit) {
        this.schoolEdit = schoolEdit;
    }

    public boolean isEditDisabled() {
        return editDisabled;
    }

    public void setEditDisabled(boolean editDisabled) {
        this.editDisabled = editDisabled;
    }

    public LazyDataModel<UpdateSchoolModel> getLazySchoolModel() {
        return lazySchoolModel;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

	public DualListModel<String> getSelectedIdsDualListModel() {
		return selectedIdsDualListModel;
	}

	public void setSelectedIdsDualListModel(DualListModel<String> selectedIdsDualListModel) {
		this.selectedIdsDualListModel = selectedIdsDualListModel;
	}
	
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


    
}
