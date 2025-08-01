package staffs.staffleave.domain.leaveBalance;

import lombok.ToString;
import staffs.common.domain.Entity;
import staffs.common.domain.Identity;

@ToString
public class LeaveBalance extends Entity {

    public LeaveBalance(Identity id) {
        super(id);
    }
    //Inherits equals (id)

    //Factory method for testing
    public static LeaveBalance leaveBalanceOf(Identity id){
        return new LeaveBalance(id);
    }

    //Domain methods to access attributes using domain language rather than get
    public Identity id(){
        return id;
    }


}
