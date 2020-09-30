package jirapojo;

public class Fields {
	private Project project;
	private String summary;
	private String description;
	private IssueType issueType;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project p) {
		this.project = p;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	
}
