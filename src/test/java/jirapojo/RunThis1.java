package jirapojo;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;

public class RunThis1 {
	public static final String SESSION_URL = "rest/auth/1/session";
	public static final String CREATE_TICKET = "rest/api/2/issue";
	public static final String uploadFile = "rest/api/2/issue/JIR-4/attachments";

	public static void main(String[] args) {

		Session session = new Session();
		session.setUsername("sushildoiphode");
		session.setPassword("9881549281");

		IssueType issue = new IssueType();
		issue.setName("Story");

		Project project = new Project();
		project.setKey("JIR");

		Fields field = new Fields();
		field.setProject(project);
		field.setIssueType(issue);
		field.setSummary("Creating a new story for false ceiling using POJO");
		field.setDescription("Living room false ceiling POJO");

		Ticket ticket = new Ticket();
		ticket.setFields(field);

		SessionFilter filter = new SessionFilter();

		RestAssured.baseURI = "http://localhost:8080/";
		given()
		.contentType(ContentType.JSON)
		.body(session)
		.log().all()
		.filter(filter)
		.when()
		.post(SESSION_URL)
		.then()
		.assertThat()
		.statusCode(200)
		.body("session.value", Matchers.notNullValue())
		.log().all();

		given()
		.contentType(ContentType.JSON)
		.body(ticket)
		.log().all()
		.filter(filter)
		.when()
		.post(CREATE_TICKET)
		.then()
		.log().all();
	}

}