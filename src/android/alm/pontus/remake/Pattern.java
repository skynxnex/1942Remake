package android.alm.pontus.remake;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.R;
import android.os.Handler;

public class Pattern {

	public Map<Integer, BreakManager> patterns = new HashMap<Integer, BreakManager>();

	public Pattern() {
		this.generatePatterns();
	}

	private void generatePatterns() {
//		// A new breakmanager for the map
//		BreakManager breakManager = new BreakManager();
//		breakManager.nrOfPlanes = 6;
//		breakManager.deltaX = 0;
//		breakManager.deltaY = 1;
//		breakManager.startX = 50;
//		breakManager.startY = 1;
//		breakManager.Xmod = 1;
//		breakManager.Ymod = -60;
//		// A break to put in the breakmanager list
//		Break break1 = new Break(50,100,2,0, false);
//		Break break2 = new Break(200, 100, 0, -1, false);
//		Break break3 = new Break(200, -100, 0, 0, true);
//		// Add the break to the breakmanager list
//		breakManager.breakList.add(break1);
//		breakManager.breakList.add(break2);
//		breakManager.breakList.add(break3);
//		patterns.put(0, breakManager);
//
//		// put the pattern in the pattern map
//		BreakManager br = new BreakManager();
//		br.nrOfPlanes = 5;
//		br.deltaX = 1;
//		br.deltaY = 0;
//		br.startX = -30;
//		br.startY = 100;
//		br.Xmod = -60;
//		br.Ymod = 1;
//		Break break4 = new Break(Panel.screenWidth + 50, 100, 1, 1, true);
//		br.breakList.add(break4);
//		patterns.put(1, br);

		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("patterns.xml");

		try {

			Document document = builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List<?> list = rootNode.getChildren("breakManager");

			for (int i = 0; i < list.size(); i++) { // loop for all the BreakManagers
				BreakManager breakManager = new BreakManager();
				Element br = (Element) list.get(i);
				List<?> breaks = br.getChildren("breaks");
				breakManager.nrOfPlanes = Integer.parseInt(br.getChildText("noPlanes"));
				breakManager.startX = Integer.parseInt(br.getChildText("startX"));
				breakManager.startY = Integer.parseInt(br.getChildText("startY"));
				breakManager.deltaX = Integer.parseInt(br.getChildText("deltaX"));
				breakManager.deltaY = Integer.parseInt(br.getChildText("deltaY"));
				breakManager.Xmod = Integer.parseInt(br.getChildText("Xmod"));
				breakManager.Ymod = Integer.parseInt(br.getChildText("Ymod"));
				
				for(int j = 0; i < breaks.size(); j++) { // loop for all the breaks
					Element breaksList = (Element) breaks.get(j);
					List<?> b = breaksList.getChildren("break");
					for(int k = 0; k < b.size(); k++) { // loop for the break info
						Break B = new Break(
								Integer.parseInt(breaksList.getChildText("X")),
								Integer.parseInt(breaksList.getChildText("Y")),
								Integer.parseInt(breaksList.getChildText("deltaX")),
								Integer.parseInt(breaksList.getChildText("deltaY")),
								false
								);
						breakManager.breakList.add(B);
					}
				}
				
				patterns.put(i, breakManager);
			}

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}

		//		Log.e("MAP", ""+patterns.keySet());
		//		Log.e("MAP ENTRY", ""+patterns.entrySet());
	}

	public int getRandomPattern() {
		Random gen = new Random();
		int value = gen.nextInt(patterns.size());
		//		Log.e("RAND VAL", ""+value);
		return value;
	}

	public void setUpdatedPosition(EnemyPlane plane) {
		BreakManager br = Panel.pattern.patterns.get(plane.getMovingPattern());
		for(Break breaker : br.breakList) {
			if(plane.getX() == breaker.X && plane.getY() == breaker.Y) {
				plane.deltaX = breaker.deltaX;
				plane.deltaY = breaker.deltaY;
				if(breaker.last) {
					plane.deleted = true;
				}
			}
		}
	}
}
