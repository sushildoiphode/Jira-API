package jirapojo;
import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;

public class RunThis {
	
	public static final String SESSION_URL = "rest/auth/1/session";
	
	public static final String CREATE_TICKET = "rest/api/2/issue";
	
	public static void main(String[] args) {

	Session session=new Session();
	session.setUsername("sushildoiphode");
	session.setPassword("9881549281");
	
	Project project=new Project();
	project.setKey("JIR");
	
	IssueType issuetype=new IssueType();
	issuetype.setName("Story");
	
	Fields fields=new Fields();
	fields.setProject(project);
	fields.setSummary("Creating a new story for false ceiling 2");
	fields.setDescription("Living room false ceiling");
	fields.setIssueType(issuetype);
	
	Ticket ticket=new Ticket();
	ticket.setFields(fields);
	
	SessionFilter filter=new SessionFilter();
	
	RestAssured.baseURI="http://localhost:8080/";
	given()
	.contentType(ContentType.JSON)
	.body(session)
	.filter(filter)
	.log().all()
	.when()
	.post("rest/auth/1/session")
	.then()
	.assertThat()
	.statusCode(200)
	.body("session.name", Matchers.equalTo("JSESSIONID"))
	.body("session.value", Matchers.notNullValue())
	.log().all();
	
	
	given()
	.contentType(ContentType.JSON)
	.body(ticket)
	.log().all()
	.filter(filter)
	.when()
	.post("rest/api/2/issue")
	.then()
	.assertThat()
	.statusCode(201)
	.log().all();
	
	
	}
}