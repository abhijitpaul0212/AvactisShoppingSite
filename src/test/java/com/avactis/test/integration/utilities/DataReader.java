package com.avactis.test.integration.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataReader 
{
	public static Collection<String[]> getDataFromXML(String filename)
	{
    	List<String[]> records = new ArrayList<String[]>();
		String element, element1, element2, element3, element4, element5, element6, element7, element8, element9;		
		
		try
		{
			File inputFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder =  dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("avactisTest");
			int totNodes =  nList.getLength();
			if (nList != null && totNodes > 0)
			{
				for(int i = 0 ; i < totNodes; i++)
				{
					Node nNode = nList.item(i);
				
					if (nNode.getNodeType() == Node.ELEMENT_NODE) 
					{
			               Element eElement = (Element) nNode;
			               element1 = eElement.getElementsByTagName("Product_Page_Content").item(0).getTextContent();
			               element2 = eElement.getElementsByTagName("categoryID_Cid").item(0).getTextContent();
			               element3 = eElement.getElementsByTagName("Product_Category").item(0).getTextContent();
			               element4 = eElement.getElementsByTagName("Cid_Sub").item(0).getTextContent();
			               element5 = eElement.getElementsByTagName("Product_Subcategory").item(0).getTextContent();
			               element6 = eElement.getElementsByTagName("Pid").item(0).getTextContent();
			               element7 = eElement.getElementsByTagName("Payment_Method").item(0).getTextContent();
			               element8 = eElement.getElementsByTagName("Shipment_Method").item(0).getTextContent();
			               element9 = eElement.getElementsByTagName("More_Expected").item(0).getTextContent();		               
			               
			               element = element1+","+element2+","+element3+","+element4+","+element5+","+element6+","+element7+","+element8+","+element9;
			               
			               System.out.println("Element: "+element);
			               String fields[] = element.split(","); 
						   records.add(fields);
					}
				}
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		return records;
	}
	
	
	/*public static Collection<String[]> getDataFromXML(String filename, String key)
	{
    	List<String[]> records = new ArrayList<String[]>();
    	//List<String[]> records2 = new ArrayList<String[]>();
		String element, element1, element2, element3, element4, element5, element6, element7, element8, element9;		
		System.out.println("Key = "+key);
		try
		{
			File inputFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder =  dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("avactisTest");
			int totNodes =  nList.getLength();
			if (nList != null && totNodes > 0)
			{
				for(int i = 0 ; i < totNodes; i++)
				{
					Node nNode = nList.item(i);
				
					if (nNode.getNodeType() == Node.ELEMENT_NODE) 
					{
			               Element eElement = (Element) nNode;
			               System.out.println(eElement.getAttribute("testData"));
			               if (eElement.getAttribute("testData").equals(key))
			               {
				               element1 = eElement.getElementsByTagName("Product_Page_Content").item(0).getTextContent();
				               element2 = eElement.getElementsByTagName("categoryID_Cid").item(0).getTextContent();
				               element3 = eElement.getElementsByTagName("Product_Category").item(0).getTextContent();
				               element4 = eElement.getElementsByTagName("Cid_Sub").item(0).getTextContent();
				               element5 = eElement.getElementsByTagName("Product_Subcategory").item(0).getTextContent();
				               element6 = eElement.getElementsByTagName("Pid").item(0).getTextContent();
				               //element7 = eElement.getElementsByTagName("Payment_Method").item(0).getTextContent();
				               //element8 = eElement.getElementsByTagName("Shipment_Method").item(0).getTextContent();
				               element9 = eElement.getElementsByTagName("More_Expected").item(0).getTextContent();		               
				               
				               element = element1+","+element2+","+element3+","+element4+","+element5+","+element6+","+element9;
				               
				               System.out.println("Element: "+element);
				               String fields[] = element.split(","); 
							   records.add(fields);
			               }
			               else if (eElement.getAttribute("testData").equals(key))
			               {
				               element1 = eElement.getElementsByTagName("Payment_Method").item(0).getTextContent();
				               element2 = eElement.getElementsByTagName("Shipment_Method").item(0).getTextContent();				               	               
				               
				               element = element1+","+element2;
				               
					           System.out.println("Element: "+element);
				               String fields[] = element.split(","); 
							   records.add(fields);
				            }
			               
					}
				}
			}
			}
		catch(Exception e)
		{e.printStackTrace();}
		return records;
	}*/
}
