package com.scfenzhi.tools;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * @author DaiYi
 * @create 2021/4/11
 */
public class RobotTools {

    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void click(int button, int delayTime) {
        robot.mousePress(button);
        robot.delay(delayTime);
        robot.mouseRelease(button);
    }


    public static void pressKey(int key, int delayTime) {
        robot.keyPress(key);
        robot.delay(delayTime);
        robot.keyRelease(key);
    }

    public static void pressKey(int key, int count, int delayTime, int intervalTime) {
        for (int i = 0; i < count; i++) {
            robot.mousePress(key);
            robot.delay(delayTime);
            robot.keyRelease(key);
            robot.delay(intervalTime);
        }
    }


    public static void doubleClick(int delayTime, int intervalTime) {
        pressKey(KeyEvent.BUTTON1_MASK, delayTime);
        robot.delay(intervalTime);
        pressKey(KeyEvent.BUTTON1_MASK, delayTime);
    }


    public static void pressShortcuts(int delayTime, int intervalTime, int... keys) {
        for (int key : keys) {
            robot.keyPress(key);
        }

        robot.delay(delayTime);

        for (int i = keys.length - 1; i >= 0; i--) {
            robot.keyRelease(keys[i]);
        }
//        for (int key : keys) {
//            robot.keyPress(key);
//            robot.delay(delayTime);
//        }
//
//        robot.delay(intervalTime);
//
//        for (int key : keys) {
//            robot.keyRelease(key);
//            robot.delay(delayTime);
//        }
    }


    public static Robot getRobot() {
        return robot;
    }

    public static void setRobot(Robot robot) {
        RobotTools.robot = robot;
    }


    public static String getSysClipboardText() {
        String ret = "";
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪切板中的内容
        Transferable clipTf = sysClip.getContents(null);

        if (clipTf != null) {
            // 检查内容是否是文本类型
            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    ret = (String) clipTf
                            .getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return ret;
    }

    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);  //覆盖系统剪切板
        clip.setContents(tText, null);
    }
}
