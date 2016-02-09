import java.util.Date;
import java.util.Collection;

public class Message {

	public String subject;

	public String body;

	public Date sentMoment;

	private Collection<Folder> folder;

	public Actor sender;

	public Collection<Actor> recipient;

}
