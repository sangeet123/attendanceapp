package attendanceapp.constants;

public final class SchoolRestControllerConstants {

	// URI Constants
	public static final String ROOT = "admin/school";
	public static final String GET_SCHOOL = "/{id}";
	public static final String GET_SCHOOL_LIST = "";
	public static final String DELETE_SCHOOL = "/delete/{id}";
	public static final String DELETE_SCHOOL_LIST = "/delete";
	public static final String CREATE_SCHOOL = "/create";
	public static final String UPDATE_SCHOOL = "/update";

	// REST Operation message Constants
	public static final String DELETE_SUCCESS = "School has been deleted successfully.";
	public static final String SELECTED_SCHOOL_DELETE_SUCCESS = "Schools have been deleted successfully.";
	public static final String DELETE_FAILURE = "School could not be deleted.";
	public static final String CREATE_SUCCESS = "School created sucessfully.";

	private SchoolRestControllerConstants() throws InstantiationException {
		throw new InstantiationException();
	}

}
