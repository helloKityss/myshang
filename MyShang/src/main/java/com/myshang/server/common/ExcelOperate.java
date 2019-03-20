package com.myshang.server.common;



import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelOperate {

    public void createExcel(List<HashMap<String,Object>> list,String path) {
        try {
            WritableWorkbook wwb = null;
               // 创建可写入的Excel工作簿
               String fileName = path+"/"+"tixian.xls";
               File file=new File(fileName);
               if (!file.exists()) {
                   file.createNewFile();
               }
               //以fileName为文件名来创建一个Workbook
               wwb = Workbook.createWorkbook(file);

               // 创建工作表
               WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
               
               //要插入到的Excel表格的行号，默认从0开始
               Label labelWitid= new Label(0, 0, "提现id(witid)");//表示第
               Label labelUserId= new Label(1, 0, "姓名id(userId)");
               Label labelBankcardId= new Label(2, 0, "提现银行卡ID(bankcardId)");
               Label labelWithdrawMoney= new Label(3, 0, "提现金额(withdrawMoney)");
               Label labelWithdrawTime= new Label(4, 0, "提现时间(withdrawTime)");
               Label labelPhoneNumber= new Label(5, 0, "手机号码(phoneNumber)");
               Label labelRoleName= new Label(6, 0, "用户角色(roleName)");
               Label labelUserName= new Label(7, 0, "提现姓名(userName)");
               Label labelCardNumber= new Label(8, 0, "银行卡号(cardNumber)");
               Label labelBankName= new Label(9, 0, "银行名称(bankName)");
               Label labelBranchName= new Label(10, 0, "开户行名称(branchName)");
               
               ws.addCell(labelWitid);
               ws.addCell(labelUserId);
               ws.addCell(labelBankcardId);
               ws.addCell(labelWithdrawMoney);
               ws.addCell(labelWithdrawTime);
               ws.addCell(labelPhoneNumber);
               ws.addCell(labelRoleName);
               ws.addCell(labelUserName);
               ws.addCell(labelCardNumber);
               ws.addCell(labelBankName);
               ws.addCell(labelBranchName);
               for (int i = 0; i < list.size(); i++) {
                   Map<String,Object> map=(Map<String,Object>) list.get(i);
                   Label labelWitid_i= new Label(0, i+1,map.get("witid")+"");
                   Label labelUserId_i= new Label(1, i+1, map.get("userId")+"");
                   Label labelBankcardId_i= new Label(2, i+1, map.get("bankcardId")+"");
                   Label labelWithdrawMoney_i= new Label(3, i+1, map.get("withdrawMoney")+"");
                   Label labelWithdrawTime_i= new Label(4, i+1, map.get("withdrawTime")+"");
                   Label labelPhoneNumber_i= new Label(5, i+1, map.get("phoneNumber")+"");
                   Label labelRoleName_i= new Label(6, i+1, map.get("roleName")+"");
                   Label labelUserName_i= new Label(7, i+1, map.get("userName")+"");
                   Label labelCardNumber_i= new Label(8, i+1, map.get("cardNumber")+"");
                   Label labelBankName_i= new Label(9, i+1, map.get("bankName")+"");
                   Label labelBranchName_i= new Label(10, i+1, map.get("branchName")+"");
                   ws.addCell(labelWitid_i);
                   ws.addCell(labelUserId_i);
                   ws.addCell(labelBankcardId_i);
                   ws.addCell(labelWithdrawMoney_i);
                   ws.addCell(labelWithdrawTime_i);
                   ws.addCell(labelPhoneNumber_i);
                   ws.addCell(labelUserName_i);
                   ws.addCell(labelRoleName_i);
                   ws.addCell(labelCardNumber_i);
                   ws.addCell(labelBankName_i);
                   ws.addCell(labelBranchName_i);
               }
             
              //写进文档
               wwb.write();
              // 关闭Excel工作簿对象
               wwb.close();
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}