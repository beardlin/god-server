package net.lantrack.project.jasperreport.common;

import org.apache.commons.lang3.StringUtils;

public class DocTypeUtils {

    /**
     * 默认类型pdf
     *
     * @param docType
     * @return
     */
    public static DocType getEnumDocType(String docType) {
        DocType type = DocType.PDF;
        if (StringUtils.isBlank(docType)){
            return type;
        }
        docType = docType.toUpperCase();
        if (docType.equals("DOC")) {
            type = DocType.DOC;
        } else if (docType.equals("XLS")) {
            type = DocType.XLS;
        } else if (docType.equals("XLSX")) {
            type = DocType.XLSX;
        } else if (docType.equals("XML")) {
            type = DocType.XML;
        } else if (docType.equals("RTF")) {
            type = DocType.RTF;
        } else if (docType.equals("CSV")) {
            type = DocType.CSV;
        } else if (docType.equals("HTML")) {
            type = DocType.HTML;
        } else if (docType.equals("TXT")) {
            type = DocType.TXT;
        }
        return type;
    }
}
