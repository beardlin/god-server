/*
 * Copyright 2017 Lantrack Corporation All Rights Reserved.
 *
 * The source code contained or described herein and all documents related to
 * the source code ("Material") are owned by Lantrack Corporation or its suppliers
 * or licensors. Title to the Material remains with Lantrack Corporation or its
 * suppliers and licensors. The Material contains trade secrets and proprietary
 * and confidential information of Lantrack or its suppliers and licensors. The
 * Material is protected by worldwide copyright and trade secret laws and
 * treaty provisions.
 * No part of the Material may be used, copied, reproduced, modified, published
 * , uploaded, posted, transmitted, distributed, or disclosed in any way
 * without Lantrack's prior express written permission.
 *
 * No license under any patent, copyright, trade secret or other intellectual
 * property right is granted to or conferred upon you by disclosure or delivery
 * of the Materials, either expressly, by implication, inducement, estoppel or
 * otherwise. Any license under such intellectual property rights must be
 * express and approved by Intel in writing.
 *
 */

package net.lantrack.framework.core.importexport;

import net.lantrack.framework.core.importexport.util.ExcelAnno;
import net.lantrack.framework.core.importexport.util.ExcleModel;

/**
 * 检测报告模板 2018年2月22日
 * 
 * @author lin
 */
@ExcleModel(path="../../../../../template/report.xlsx")
public class Report {
    @ExcelAnno("报告编号")
    private String reportCode;
    @ExcelAnno("受检单位")
    private String companyName;
    @ExcelAnno("单位地址")
    private String companyAddress;
    @ExcelAnno("样品名称")
    private String sampleName;
    @ExcelAnno("样品编号")
    private String sampleCode;
    
    public Report() {
        
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    @Override
    public String toString() {
        return "Report [reportCode=" + reportCode + ", companyName=" + companyName
                + ", companyAddress=" + companyAddress + ", sampleName=" + sampleName
                + ", sampleCode=" + sampleCode + "]";
    }
    
    
    
    
}
