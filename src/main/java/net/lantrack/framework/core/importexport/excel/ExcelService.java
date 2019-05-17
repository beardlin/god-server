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

package net.lantrack.framework.core.importexport.excel;

import java.io.File;
import java.util.List;

/**
 * excel导入导出接口 2018年2月6日
 * 
 * @author lin
 */
public interface ExcelService {
    
    List importExcel(Class excelModel,File excel,int startRow);
    
    boolean validateFile(Class excelModel,File excel);
    
    /**
     * @param modelClass 往前台返回的数据model
     * @param headers 导出的列
     * @param datas  导出的列的数据
     * @return 
     * 2018年2月22日
     * @author lin
     */
    String export(Class modelClass, 
            List<String> headers, List<?> datas);
    
    
    
}
