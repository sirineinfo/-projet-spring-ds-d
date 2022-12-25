package de.tekup.studentsabsence.services;

import de.tekup.studentsabsence.entities.Group;
import de.tekup.studentsabsence.entities.GroupSubject;
import de.tekup.studentsabsence.entities.Subject;

import java.util.List;

public interface GroupSubjectService {
    void addSubjectToGroup(Group group, Subject subject, float hours);
    List<GroupSubject> getSubjectsByGroupId(Long id);
    void deleteSubjectFromGroup(Long gid, Long sid);

    // Question 1)
    Subject getSubjectByGroupHavingMaxAbsence(List<GroupSubject> groupSubjects);
    Subject getSubjectByGroupHavingMinAbsence(List<GroupSubject> groupSubjects);

    //Question 2)
    List<GroupSubject> getSubjectsGroupBySubjectId(Long sid);

    GroupSubject getSubjectsGroupBySubjectIdAndGroupId(Long sid,Long gid);
}
