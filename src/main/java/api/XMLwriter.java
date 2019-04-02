package api;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLwriter {

    public void WriteNewXmlFile(String name, String developer, String publisher, String metacritic, String genre, String accessibility, String platform, String platform2, String path) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Games");
            doc.appendChild(rootElement);

            // staff elements
            Element PS4games = doc.createElement(platform);
            rootElement.appendChild(PS4games);

            Element PS4game = doc.createElement(platform2);
            PS4games.appendChild(PS4game);

            // set attribute to staff element

            PS4game.setAttribute("name", name);
            PS4game.setAttribute("developer", developer);
            PS4game.setAttribute("publisher", publisher);
            PS4game.setAttribute("metacritic", metacritic);
            PS4game.setAttribute("genre", genre);
            PS4game.setAttribute("accessibility", accessibility);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));

            // Output to console for testing
            //result = new StreamResult(System.out);
            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void XmlCopier(Document document, String copyPath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(copyPath));
            transformer.transform(source, result);
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void XmlAppender(Document document, String platform, String platform2, String name, String developer, String publisher, String metacritic, String genre, String accessibility) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Element rootElement = document.getDocumentElement();
            Element MainPlatform = document.createElement(platform);
            Element SubPlatform = document.createElement(platform2);

           /* Genre[] genres = Genre.values();
            Boolean validGenre = true;
            for (Genre g : genres) {
                if (g.equals(genre)) {
                } else {
                    while (validGenre) {
                        System.out.println("Give a valid genre");
                        validGenre = false;
                        Scanner sc = new Scanner(System.in);
                        genre = sc.nextLine();
                    }
                }
            }*/

            SubPlatform.setAttribute("name", name);
            SubPlatform.setAttribute("developer", developer);
            SubPlatform.setAttribute("publisher", publisher);
            SubPlatform.setAttribute("metacritic", metacritic);
            SubPlatform.setAttribute("genre", genre);
            SubPlatform.setAttribute("accessibility", accessibility);

            rootElement.appendChild(MainPlatform);
            //rootElement.setTextContent("\n");
            MainPlatform.appendChild(SubPlatform);
            //MainPlatform.setTextContent("\n");

            document.replaceChild(rootElement, rootElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult("/home/beszteri/Dokumentumok/Codecool/java/ModellingReworked/src/main/resources/Games.xml");
            transformer.transform(source, result);


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /*public void XmlAppender2(Document document, String platform2, String name, String developer, String publisher, String metacritic, String genre, String accessibility) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Element rootElement = document.getDocumentElement();
            Element SubPlatform = document.createElement(platform2);

            Genre[] genres = Genre.values();
            Boolean validGenre = true;
            for (Genre g : genres) {
                if (g.equals(genre)) {
                } else {
                    while (validGenre) {
                        System.out.println("Give a valid genre");
                        validGenre = false;
                        Scanner sc = new Scanner(System.in);
                        genre = sc.nextLine();
                    }
                }
            }

            SubPlatform.setAttribute("name", name);
            SubPlatform.setAttribute("developer", developer);
            SubPlatform.setAttribute("publisher", publisher);
            SubPlatform.setAttribute("metacritic", metacritic);
            SubPlatform.setAttribute("genre", genre);
            SubPlatform.setAttribute("accessibility", accessibility);

            rootElement.appendChild(SubPlatform);
            //MainPlatform.setTextContent("\n");

            document.replaceChild(rootElement, rootElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult("/home/beszteri/Dokumentumok/Codecool/java/ModellingReworked/src/main/resources/Games.xml");
            transformer.transform(source, result);


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }*/
}