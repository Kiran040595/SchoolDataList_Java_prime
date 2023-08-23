


package com.schoolservicepkg;

import java.util.List;

import com.schoolmodel.SchoolModel;

public interface UpdateSchoolServicePkg {
    public SchoolModel createSchool(SchoolModel school);
    public List<SchoolModel> findAll();
    public void deleteSchool(SchoolModel school);
    public SchoolModel updateSchool(SchoolModel school);
    public SchoolModel editSchool(SchoolModel school);
}
