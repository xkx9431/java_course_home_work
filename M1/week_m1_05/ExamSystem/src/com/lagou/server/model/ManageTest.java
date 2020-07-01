package com.lagou.server.model;

import com.lagou.model.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ManageTest {

    private ArrayList<Test> tests = new ArrayList<>();
    private HashSet<String> testIDs = new HashSet<>();
    private String filePath = "d://tests.dat";

    //静态代码块，初始化先加载上次已经试题
    {
        System.out.println("test manage system init");
        System.out.println("加载之前的测试.....");
        // loadTest();
    }


    /**
     * 当前操作的试题instance
     * 只有在查询是将该变量有效
     * 跟新操作需要先执行查询
     */
    private Test currTest;


    /**
     * 向试题系统中添加试题
     * @param newTestID: 题号
     * @param newTestContent: 测试内容
     * @param newTestAnswer: 测试答案
     * @param newTestScore: 分值
     */
    public void addTest(String newTestID,String newTestContent,String newTestAnswer,int newTestScore ) throws  Exception {
        Test newTest = null;
        newTest = new Test( newTestID, newTestContent, newTestAnswer, newTestScore);


        if(testIDs.contains(newTest.getTestNo())){
            System.out.println("当前试题" + newTest.getTestNo() + "已经存在");
        } else {
            this.tests.add(newTest);
            this.testIDs.add(newTest.getTestNo());
            System.out.println("当前试题" + newTest + "已经成功添加");
        }
    }

    public void addTest( Test newTest) {


        if(testIDs.contains(newTest.getTestNo())){
            System.out.println("当前试题" + newTest.getTestNo() + "已经存在");
        } else {
            this.tests.add(newTest);
            this.testIDs.add(newTest.getTestNo());
            System.out.println("当前试题" + newTest + "已经成功添加");
        }
    }
    /**
     * 向測試題系统中删除測試題,依据学号操作
     * @param testID
     *
     */
    public void removeTest(String testID){
        if(testIDs.contains(testID)){
            this.tests.remove( getTestByID(testID));
            this.testIDs.remove(testID);
            System.out.println("当前測試題" + testID + "已经成功删除");
        }else {
            System.out.printf("当前測試題" + testID + "不存在");
        }
    }

    /**
     * 向学生系统中更改学生信息
     * （该步骤前需要先执行一次查询操作）
     * @param testID 最新的学号
     * @param testContent 最新的測試內容
     * @param testAnswer 最新的测试答案
     * @param testScore 最新的测试分数
     *
     */
    public void updateTest(String testID , String testContent, String testAnswer, int testScore ){
        if(!testID.equals("")){
            this.testIDs.remove(this.currTest.getTestNo());
            try {
                this.currTest.setTestNo(testID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.testIDs.add(this.currTest.getTestNo());
        }

        this.currTest.setAnswer( testAnswer);
        this.currTest.setContent( testContent);
        this.currTest.setScore(testScore);

        System.out.println("跟新如下！");
        showCurrTest();
        this.currTest = null;

    }
    /**
     * 查找 //仅支持题号，题目内容
     * 优先学号查找
     * @param testID
     * @param testName
     *
     */
    public void searchStudent(String testID , String testName ){
//        if(!testID.equals("")){
            this.currTest =  getTestByID(testID);
            if(this.currTest==null){
                System.out.println("当前试题不存在！");

            }else{
                showCurrTest();
            }
    }
    public String searchStudent(String testID  ){

        this.currTest =  getTestByID(testID);
        if(this.currTest==null){
            System.out.println("当前试题不存在！");
            return "-1";

        }else{
            return this.currTest.toString();
        }

    }

    /**
     * @param testContent
     * @return test
     * 依据试题内容，返回找到试题
     */
    private Test getTestByTestContent(String testContent) {
        for (Iterator<Test> iterator = tests.iterator(); iterator.hasNext(); ) {
            Test curStu = iterator.next();
            if(curStu.getContent().equals(testContent) ){
                return curStu;
            }
        }
        return null;
    }

    /**
     * @param testID
     * @return test
     *  依据学生查找，返回找到目标学生
     */
    private Test getTestByID(String testID) {
        for (Iterator<Test> iterator = tests.iterator(); iterator.hasNext(); ) {
            Test curTest = iterator.next();
            if(curTest.getTestNo().equals(testID)){
                return curTest;
            }
        }
        return null;
    }

    /**
     * 遍历所有学生信息
     * 基于 iterator
     */
    public void showAll() {
        for (Iterator<Test> iterator = tests.iterator(); iterator.hasNext(); ) {
            Test curTest = iterator.next();
            System.out.println(curTest);
        }
    }

    public Test showCurrTest() {
        return currTest;
    }

    void  showCurrStudent() {
        System.out.println("当前试题为：");
        System.out.println(  this.currTest );
    }


    //关闭试题操作时，讲对应的试题信息保存到“d:test.dat”

    public void saveTests(){
        System.out.println("当前试题有：");
        System.out.println(this.tests);
        ObjectOutputStream oos = null;
        try {
            // 1.创建ObjectOutputStream类型的对象与d:test.dat文件关联
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            // 2.准备一个User类型的对象并初始化
            oos.writeObject(this.tests);
            System.out.println("试题信息保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流对象并释放有关的资源
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadTest() {
        ObjectInputStream ois = null;
        File file = new File(filePath);
        if(!file.exists()){
            return;
        }

        try {
            // 1.创建ObjectInputStream类型的对象与d:/a.dat文件关联
            ois = new ObjectInputStream(new FileInputStream(filePath));
            // 2.从输入流中读取一个对象并打印
            ArrayList<Test> tmpobj = ( ArrayList<Test> ) ois.readObject();
            System.out.println("读取的测试题目为：" + tmpobj);
            this.tests = ( ArrayList<Test> ) tmpobj.clone();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3.关闭流对象并释放有关的资源
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
