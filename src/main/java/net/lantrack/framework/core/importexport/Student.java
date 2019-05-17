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
 * 测试学生表导入Model，在实际开发过程中将Model放在应在的位置，别往这放
 * 注意：模板的path要写对
 * 2018年2月22日
 * @author lin
 */
@ExcleModel(path="../../../../../template/student.xlsx")
public class Student{
    @ExcelAnno("姓名")
    private String stuName;
    @ExcelAnno("年龄")
    private int stuAge;
    @ExcelAnno("生日")
    private String stuBirthday;
    
    public Student() {
        
    }
    
    public Student(String stuName, int stuAge, String stuBirthday) {
        super();
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.stuBirthday = stuBirthday;
    }

    public String getStuName() {
        return stuName;
    }
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
    public int getStuAge() {
        return stuAge;
    }
    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }
    public String getStuBirthday() {
        return stuBirthday;
    }
    public void setStuBirthday(String stuBirthday) {
        this.stuBirthday = stuBirthday;
    }
    @Override
    public String toString() {
        return "Student [stuName=" + stuName + ", stuAge=" + stuAge + ", stuBirthday="
                + stuBirthday + "]";
    }
    
    
}
