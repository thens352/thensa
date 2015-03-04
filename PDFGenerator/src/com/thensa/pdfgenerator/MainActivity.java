package com.thensa.pdfgenerator;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.cete.dynamicpdf.Document;
import com.cete.dynamicpdf.Font;
import com.cete.dynamicpdf.Page;
import com.cete.dynamicpdf.PageOrientation;
import com.cete.dynamicpdf.PageSize;
import com.cete.dynamicpdf.TextAlign;
import com.cete.dynamicpdf.pageelements.Label;

public class MainActivity extends Activity {
	private static String FILE = Environment.getExternalStorageDirectory()
			+ "/HelloWorld.pdf";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create a document and set it's properties
		Document objDocument = new Document();
		objDocument.setCreator("DynamicPDFHelloWorld.java");
		objDocument.setAuthor("Your Name");
		objDocument.setTitle("Hello World");

		// Create a page to add to the document
		Page objPage = new Page(PageSize.LETTER, PageOrientation.PORTRAIT,
				54.0f);

		// Create a Label to add to the page
		String strText = "Hello World...\nFrom DynamicPDF Generator "
				+ "for Java\nDynamicPDF.com";
		Label objLabel = new Label(strText, 0, 0, 504, 100,
				Font.getHelvetica(), 18, TextAlign.CENTER);

		// Add label to page
		objPage.getElements().add(objLabel);

		// Add page to document
		objDocument.getPages().add(objPage);

		try {
			// Outputs the document to file
			objDocument.draw(FILE);
			Toast.makeText(this, "File has been written to :" + FILE,
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this,
					"Error, unable to write to file\n" + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}
}
