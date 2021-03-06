package attendanceapp.constants;

public final class StaffRestControllerConstants {

	// URI Constants
	public static final String ROOT = "school/{schoolId}/staff";
	public static final String GET_STAFF = "/{staffId}";
	public static final String GET_STAFF_LIST = "";
	public static final String DELETE_STAFF = "/delete/{staffId}";
	public static final String DELETE_STAFF_LIST = "/delete";
	public static final String CREATE_STAFF = "/create";
	public static final String UPDATE_STAFF = "/update";

	// REST Operation message Constants
	public static final String DELETE_SUCCESS = "Staff has been deleted successfully.";
	public static final String SUBJECTS_DELETE_SUCCESS = "Staffs have been deleted successfully.";
	public static final String DELETE_FAILURE = "Staff could not be deleted.";
	public static final String CREATE_SUCCESS = "Staff created sucessfully.";

	private StaffRestControllerConstants() throws InstantiationException {
		throw new InstantiationException();
	}

}
