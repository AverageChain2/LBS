package staffs.leavemanagement.domain.leaveRequest;

import staffs.common.domain.Entity;
import staffs.common.domain.Identity;

import lombok.ToString;

@ToString
public class LeaveRequest extends Entity {

    public LeaveRequest(Identity id) {
        super(id);
    }
    //Inherits equals (id)

    //Factory method for testing
    public static LeaveRequest leaveRequestOf(Identity id){
        return new LeaveRequest(id);
    }

    //Domain methods to access attributes using domain language rather than get
    public Identity id(){
        return id;
    }


}
