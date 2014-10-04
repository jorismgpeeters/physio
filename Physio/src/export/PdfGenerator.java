/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package export;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.text.*;

import physio.*;

/**
 *
 * @author Joris
 */
public class PdfGenerator {
    private ExerciseProgram program = null;
    private String pathName = null;
    
    // Static configuration
    static private int titleFontSize = 14;
    static private int headerSpacing = 25;
    
    public PdfGenerator(ExerciseProgram program, String pathName) {
        this.program = program;
        this.pathName = pathName;
    }

    public void generate() {
        
        Document document = new Document();

        try {
            PdfWriter.getInstance(document,
                new FileOutputStream(pathName));

            document.open();
            
            generateHeader(document);
            generatePatientInfo(document);
            
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }        
        
    }
    
    public void generateHeader(Document doc) throws DocumentException {
        Physio physio = program.getPhysio();
        
        Paragraph naam = new Paragraph();
        naam.setAlignment(Element.ALIGN_RIGHT);        
        Chunk nm = new Chunk(physio.getVoornaam() + " " + physio.getNaam());
        naam.add(nm);
        
        Paragraph email = new Paragraph();
        email.setAlignment(Element.ALIGN_RIGHT);        
        Chunk em = new Chunk(physio.getEmail());
        email.add(em);        
        email.setSpacingAfter(headerSpacing);
        
        Paragraph title = new Paragraph();
        title.setAlignment(Element.ALIGN_CENTER);  
        title.getFont().setStyle(Font.UNDERLINE);
        title.getFont().setSize(titleFontSize);
        Chunk oef = new Chunk("Oefenprogramma");
        title.add(oef);        
        title.setSpacingAfter(headerSpacing);        
        
        doc.add(naam);
        doc.add(email);
        doc.add(title);
    }
    
    private void generatePatientInfo(Document doc) throws DocumentException {
        
        Patient patient = program.getPatient();
        String fullName = patient.getVoornaam() + " " + patient.getAchternaam();
        Date datum = program.getDatum();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
        String datumString = df.format(datum);  
        PdfPTable table = new PdfPTable(2);
        
        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setBorder(PdfCell.NO_BORDER);        
        
        ArrayList<PdfPCell> cells = new ArrayList<PdfPCell>();
        PdfPCell cell1 = new PdfPCell(new Paragraph("Patiënt: "));
        PdfPCell cell2 = new PdfPCell(new Paragraph(fullName));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Datum: "));
        PdfPCell cell4 = new PdfPCell(new Paragraph(datumString));
        PdfPCell cell5 = new PdfPCell(new Paragraph("Opmerkingen: "));
        PdfPCell cell6 = new PdfPCell(new Paragraph("Indien een oefening pijn doet, mag je ze overslaan."));        
        
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);
        cells.add(cell6);

        for (PdfPCell c : cells){
            c.setBorder(PdfCell.NO_BORDER); 
            table.addCell(c);
        }
        
        float[] columnWidths = {1, 3};
        table.setWidths(columnWidths);        

        doc.add(table);        
    }
            
    
    
}
