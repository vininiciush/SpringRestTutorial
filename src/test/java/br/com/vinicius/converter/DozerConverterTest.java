package br.com.vinicius.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.vinicius.converter.mocks.MockPerson;
import br.com.vinicius.model.Person;
import br.com.vinicius.data.vo.PersonVO;

public class DozerConverterTest {
	
    MockPerson inputObject;

    @Before
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
        Assert.assertEquals(""+Long.valueOf(0L), ""+output.getId());
        Assert.assertEquals("First Name Test0", output.getFirsName());
        Assert.assertEquals("Last Name Test0", output.getLastName());
        Assert.assertEquals("Addres Test0", output.getAddress());
        Assert.assertEquals("Male", output.getGender());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = DozerConverter.parseListObject(inputObject.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);
        
        Assert.assertEquals(""+Long.valueOf(0L), ""+outputZero.getId());
        Assert.assertEquals("First Name Test0", outputZero.getFirsName());
        Assert.assertEquals("Last Name Test0", outputZero.getLastName());
        Assert.assertEquals("Addres Test0", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());
        
        PersonVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(""+Long.valueOf(0L), ""+outputZero.getId());
        Assert.assertEquals("First Name Test7", outputSeven.getFirsName());
        Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assert.assertEquals("Addres Test7", outputSeven.getAddress());
        Assert.assertEquals("Female", outputSeven.getGender());
        
        PersonVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(""+Long.valueOf(0L), ""+outputZero.getId());
        Assert.assertEquals("First Name Test12", outputTwelve.getFirsName());
        Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
        Assert.assertEquals("Addres Test12", outputTwelve.getAddress());
        Assert.assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
        Assert.assertEquals(""+Long.valueOf(0L), ""+output.getId());
        Assert.assertEquals("First Name Test0", output.getFirsName());
        Assert.assertEquals("Last Name Test0", output.getLastName());
        Assert.assertEquals("Addres Test0", output.getAddress());
        Assert.assertEquals("Male", output.getGender());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = DozerConverter.parseListObject(inputObject.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);
        
        Assert.assertEquals(""+Long.valueOf(0L), ""+outputZero.getId());
        Assert.assertEquals("First Name Test0", outputZero.getFirsName());
        Assert.assertEquals("Last Name Test0", outputZero.getLastName());
        Assert.assertEquals("Addres Test0", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());
        
        Person outputSeven = outputList.get(7);
        
        Assert.assertEquals(""+Long.valueOf(0L), ""+outputZero.getId());
        Assert.assertEquals("First Name Test7", outputSeven.getFirsName());
        Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assert.assertEquals("Addres Test7", outputSeven.getAddress());
        Assert.assertEquals("Female", outputSeven.getGender());
        
        Person outputTwelve = outputList.get(12);
        
        Assert.assertEquals(""+Long.valueOf(0L), ""+outputZero.getId());
        Assert.assertEquals("First Name Test12", outputTwelve.getFirsName());
        Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
        Assert.assertEquals("Addres Test12", outputTwelve.getAddress());
        Assert.assertEquals("Male", outputTwelve.getGender());
    }
}