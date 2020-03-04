package cn.carmen;

import java.util.*;


public class TestApplication {

    public static void main( String[] args )
    {
         //方法实现
        digitsToLetters();
    }

    /**
     * HashMap赋值
     * @return
     */
    public  static HashMap  initDigitsMap(){
        HashMap digitsMap=new HashMap();
        digitsMap.put("0",Arrays.asList(""));
        digitsMap.put("1",Arrays.asList(""));
        digitsMap.put("2",Arrays.asList("A","B","C"));
        digitsMap.put("3",Arrays.asList("D","E","F"));
        digitsMap.put("4",Arrays.asList("G","H","I"));
        digitsMap.put("5",Arrays.asList("J","K","L"));
        digitsMap.put("6",Arrays.asList("M","N","O"));
        digitsMap.put("7",Arrays.asList("P","Q","R","S"));
        digitsMap.put("8",Arrays.asList("T","U","V"));
        digitsMap.put("9",Arrays.asList("W","X","Y","Z"));
        return  digitsMap;
    }

    public static void digitsToLetters(){
        HashMap  digitsMap=initDigitsMap();
        System.out.println("Please input digits from 0 to 9:");
        //输入按键数字1-9
        String digits;

        while(true){
            Scanner input=new Scanner(System.in);
            digits=input.next();
            if(!digits.matches("[0-9]{1,2}")){
                System.out.println("输入不合法！只能输入0-99的数字,请重新输入：");
            }else{
                break;
            }
        }
        StringBuilder arrInput=new StringBuilder("Input:arr[] ={");
        String[] arrStr=digits.split("");
        List<String[]> dataList=new ArrayList<String[]>();

        for(int i=0;i<arrStr.length;i++){
            arrInput.append(arrStr[i]);
            if(i<arrStr.length-1){
                arrInput.append(",");
            }
            //先将多个list中的数据都添加到同一个集合中作为数据源
            List<String> lettersList= (List<String>) digitsMap.get(arrStr[i]);
            if(lettersList.size()>0){//没有数据的集合不能强行转换为数组
               String[] letterArr= lettersList.toArray(new String[0]);
                dataList.add(letterArr);
            }
        }
        arrInput.append("}");
        //递归实现多数组排列组合，并返回最终的排列集合
        List<String[]> resultList= makeupLetters(dataList,0,null);
        //打印输入内容
        System.out.println(arrInput.toString());
        System.out.print("Output:");
        //打印输出排列组合结果
        for(int i=0;i<resultList.size();i++){
            String[] letterArr=resultList.get(i);
            System.out.print(" ");
            for(String s: letterArr){
                System.out.print(s);
            }
        }
    }
    public static List<String[]> makeupLetters(List<String[]> dataList, int index, List<String[]> resultList){
        if(index==dataList.size()){
            return resultList;
        }
        List<String[]> resultList0=new ArrayList<String[]>();
        if(index==0){//第一列数组默认有多少个字母就添加多少个排列数据
            String[] dataArr=dataList.get(0);
            for(String s : dataArr){
                resultList0.add(new String[]{s});
            }
        }else{
            String[] dataArr=dataList.get(index);
            for(String[] dataArr0: resultList){
                for(String s : dataArr){
                    //复制数组并扩充新元素
                    String[] dataArrCopy=new String[dataArr0.length+1];
                    System.arraycopy(dataArr0, 0, dataArrCopy, 0, dataArr0.length);
                    dataArrCopy[dataArrCopy.length-1]=s;
                    //追加到结果集
                    resultList0.add(dataArrCopy);
                }
            }
        }
        return makeupLetters(dataList,++index,resultList0);
    }
}
