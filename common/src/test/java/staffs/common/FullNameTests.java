package staffs.common;

import staffs.common.domain.FullName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FullNameTests {
    private FullName generateFullName(){
        //new object - with same data each call
        return new FullName("first1","surname1");
    }

    private String generateValueOfLength(int length){
        char[] chars = new char[length];
        for (int i = 0;i<length;i++){
            chars[i]='a';
        }
        return new String(chars);
    }

    @Test
    @DisplayName("Two names with the same values are considered the same")
    void test01(){
        FullName fullName1 = generateFullName();

        FullName fullName2 = generateFullName();

        assertEquals(fullName1,fullName2);
    }

    @Test
    @DisplayName("A full name cannot contain a blank first name")
    void test02(){
        assertThrows(IllegalArgumentException.class, () -> {
            new FullName("","surname1");
        });
    }

    @Test
    @DisplayName("A first name can be up to 20 characters in length")
    void test03(){
        assertDoesNotThrow(() -> {
            new FullName(generateValueOfLength(20),"surname1");
        });
    }

    @Test
    @DisplayName("A firstname exceeding 20 characters is considered invalid")
    void test04(){
        assertThrows(IllegalArgumentException.class, () -> {
            new FullName(generateValueOfLength(21),"surname1");
        });
    }

   //same tests for surname
}
