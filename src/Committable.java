import java.util.ArrayList;

public interface Committable {

	public void commit(String fileName, ArrayList<Patient> patients);
	
}
