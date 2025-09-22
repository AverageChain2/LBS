import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import staffs.identitymanagement.appUser.AppUserAggregateTests;

@Suite
@SelectClasses( {AppUserAggregateTests.class
                })
public class TestSuite {
}
