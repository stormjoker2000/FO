import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.logging.Handler;

public class XMLHandler {

    private Document tree;

    public XMLHandler() {
        this.tree = loadXml();
    }
    private Document loadXml(){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse("daily_tasks.xml");
            doc.getDocumentElement().normalize();
            return doc;
        }
        catch (Exception e) {
            System.out.println("Could not construct Task Tree");
            return null;
        }

    }
    public void listTasks() {
        NodeList taskList = tree.getElementsByTagName("task");
        for(int i = 0; i < taskList.getLength(); i++) {
            Node current_task = taskList.item(i);
            NodeList taskElements = current_task.getChildNodes();
            NamedNodeMap attrList = current_task.getAttributes();
            for (int x = 0; x < attrList.getLength(); x++){
                Node curr_attr = attrList.item(x);
                if (curr_attr.getNodeType() == Node.ATTRIBUTE_NODE && curr_attr.getNodeName().equals("finished")){
                    if (curr_attr.getNodeValue().equals("false")) {
                        System.out.println("TODO");
                        for (int j = 0; j < taskElements.getLength(); j++) {
                            Node curr = taskElements.item(j);
                            if(curr.getNodeType() == Node.ELEMENT_NODE){
                                System.out.println(curr.getNodeName() + ": " + curr.getTextContent());
                            }
                        }
                    }
                    else {
                        System.out.println("DONE");
                        for (int j = 0; j < taskElements.getLength(); j++) {
                            Node curr = taskElements.item(j);
                            if (curr.getNodeType() == Node.ELEMENT_NODE) {
                                System.out.println(curr.getNodeName() + ": " + curr.getTextContent());
                            }
                        }
                    }
                }


            }
        }
    }
}
