package com.exammanager.core.model.dto.request;

import com.exammanager.core.model.entity.Student;
import com.exammanager.core.repository.StudentRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
public class StudentRequestFilter {

    private final FilterType filterType;


    public StudentRequestFilter(String departmentId, String groupId, String subgroupId) {
        if (departmentId == null) {
            filterType = FilterType.NON;
            return;
        }
        if (groupId == null) {
            filterType = FilterType.BY_DEPARTMENT;
            filterType.setValue(departmentId);
            return;
        }
        if (subgroupId == null) {
            filterType = FilterType.BY_GROUP;
            filterType.setValue(groupId);
            return;
        }
        filterType = FilterType.BY_SUBGROUP;
        filterType.setValue(subgroupId);
    }

    public enum FilterType {
        NON {
            @Override
            public List<Student> findByFilter(StudentRepository studentRepository) {
                return studentRepository.findAll();
            }

            @Override
            public Page<Student> findByFilter(StudentRepository repository, Pageable pageable) {
                return repository.findAll(pageable);
            }
        },
        BY_DEPARTMENT {
            @Override
            public List<Student> findByFilter(StudentRepository studentRepository) {
                return studentRepository.findAllBySubgroupGroupDepartmentId(this.getValue());
            }

            @Override
            public Page<Student> findByFilter(StudentRepository repository, Pageable pageable) {
                return repository.findAllBySubgroupGroupDepartmentId(this.getValue(), pageable);
            }
        },
        BY_GROUP {
            @Override
            public List<Student> findByFilter(StudentRepository studentRepository) {
                return studentRepository.findAllBySubgroupGroupId(this.getValue());
            }

            @Override
            public Page<Student> findByFilter(StudentRepository repository, Pageable pageable) {
                return repository.findAllBySubgroupGroupId(this.getValue(), pageable);
            }
        },
        BY_SUBGROUP {
            @Override
            public List<Student> findByFilter(StudentRepository studentRepository) {
                return studentRepository.findAllBySubgroupId(this.getValue());
            }

            @Override
            public Page<Student> findByFilter(StudentRepository repository, Pageable pageable) {
                return repository.findAllBySubgroupId(this.getValue(), pageable);
            }
        };


        private String value;

        public abstract Page<Student> findByFilter(StudentRepository repository, Pageable pageable);
        public abstract List<Student> findByFilter(StudentRepository studentRepository);

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
}
