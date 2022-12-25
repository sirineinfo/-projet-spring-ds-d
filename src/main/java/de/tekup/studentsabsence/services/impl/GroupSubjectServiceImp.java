package de.tekup.studentsabsence.services.impl;

import de.tekup.studentsabsence.entities.Group;
import de.tekup.studentsabsence.entities.GroupSubject;
import de.tekup.studentsabsence.entities.GroupSubjectKey;
import de.tekup.studentsabsence.entities.Subject;
import de.tekup.studentsabsence.repositories.GroupSubjectRepository;
import de.tekup.studentsabsence.services.AbsenceService;
import de.tekup.studentsabsence.services.GroupService;
import de.tekup.studentsabsence.services.GroupSubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class GroupSubjectServiceImp implements GroupSubjectService {
    private final GroupSubjectRepository groupSubjectRepository;
    private final GroupService groupService;

    private final AbsenceService absenceService;

    @Override
    public void addSubjectToGroup(Group group, Subject subject, float hours) {
        groupSubjectRepository.save(new GroupSubject(
                new GroupSubjectKey(group.getId(),subject.getId()),
                group,
                subject,
                hours
        ));
    }

    @Override
    public List<GroupSubject> getSubjectsByGroupId(Long id) {
        Group group = groupService.getGroupById(id);
        return new ArrayList<>(groupSubjectRepository.findAllByGroup(group));
    }

    @Override
    public void deleteSubjectFromGroup(Long gid, Long sid) {
        //TODO find a groupSubject by Group Id and Subject Id
        GroupSubject groupSubject = groupSubjectRepository.findByGroup_IdAndSubject_Id(gid,sid);

        groupSubjectRepository.delete(groupSubject);
    }
    public Subject getSubjectByGroupHavingMaxAbsence(List<GroupSubject> groupSubjects){
        Subject subjectHavingMaxAbscence = new Subject();
        float val = 0; float max = 0;
        for (GroupSubject gs: groupSubjects) {
            Long groupId = gs.getGroup().getId();
            Long subjectId = gs.getSubject().getId();
            val = absenceService.hoursCountByGroupAndSubject(groupId, subjectId);
            if(val > max){
                val = init;
                subjectHavingMaxAbscence=gs.getSubject();
            }
        }
        return subjectHavingMaxAbscence;
    }
    public Subject getSubjectByGroupHavingMinAbsence(List<GroupSubject> groupSubjects){
        Subject subjectHavingMinAbsence = new Subject();
        float val = 0;
        float subjectHavingMinAbs = 1000;
        for (GroupSubject gs:groupSubjects) {
            Long groupId = gs.getGroup().getId();
            Long subjectId = gs.getSubject().getId();
            init = absenceService.hoursCountByGroupAndSubject(groupId, subjectId);
            if(init < subjectHavingMinAbs){
                subjectHavingMinAbs = val;
                subjectHavingMinAbsence=gs.getSubject();
            }
        }
        return subjectHavingMinAbsence;
    }
    //Question 2
    public List<GroupSubject> getSubjectsGroupBySubjectId(Long sid){
        List<GroupSubject> groupSubjects=new ArrayList<>();
        groupSubjectRepository.findBySubject_Id(sid).forEach(groupSubjects::add);
        return groupSubjects;
    }
    public GroupSubject getSubjectsGroupBySubjectIdAndGroupId(Long sid,Long gid){
        GroupSubject groupSubject= groupSubjectRepository.findByGroup_IdAndSubject_Id(sid,gid);
        return groupSubject;
    }


}
