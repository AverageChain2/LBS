package staffs.staffleave;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import staffs.staffleave.leaveBalance.LeaveBalanceAggregateTests;
import staffs.staffleave.leaveRequest.LeaveRequestAggregateTests;

@Suite
@SelectClasses( {LeaveBalanceAggregateTests.class,
                LeaveRequestAggregateTests.class,
                })
public class TestSuite {
}
