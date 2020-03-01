package entity;

public class SubjectTest {

    private Long subjectId;
    private Long testId;

    public SubjectTest(Long subjectId, Long testId) {
        this.subjectId = subjectId;
        this.testId = testId;
    }

    public SubjectTest() {
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "SubjectTest{" +
                "subject_id=" + subjectId +
                ", test_id=" + testId +
                '}';
    }
}
