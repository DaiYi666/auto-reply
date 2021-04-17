package com.scfenzhi.controller;

import com.scfenzhi.tools.EmailSender;
import com.scfenzhi.tools.Reply;
import com.scfenzhi.tools.RobotTools;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author DaiYi
 * @Date 2021/4/14 10:28
 */
@RestController
public class AutoReplyController {

    @Resource
    private EmailSender emailSender;

    private static int executionsCount;

    private static int replyCount;

    private static boolean isRun;

    @GetMapping("/startAutoReplyTool")
    public int startAutoReplyTool() {
        new Thread(() -> {
            try {
                Robot robot = RobotTools.getRobot();
                RobotTools.setSysClipboardText("");
                AutoReplyController.isRun = true;
                robot.delay(2000);
                while (AutoReplyController.isRun) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.delay(50);
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.delay(50);
                    robot.keyRelease(KeyEvent.VK_TAB);
                    robot.delay(50);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.delay(200);

                    robot.mouseMove(740, 220);
                    robot.delay(200);

                    robot.mousePress(KeyEvent.BUTTON1_MASK);
                    robot.delay(50);
                    robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                    robot.delay(50);
                    robot.mousePress(KeyEvent.BUTTON1_MASK);
                    robot.delay(50);
                    robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                    robot.delay(50);
                    robot.mousePress(KeyEvent.BUTTON1_MASK);
                    robot.delay(50);
                    robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                    robot.delay(200);

                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.delay(50);
                    robot.keyPress(KeyEvent.VK_C);
                    robot.delay(50);
                    robot.keyRelease(KeyEvent.VK_C);
                    robot.delay(50);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.delay(50);

                    String sysClipboardText = RobotTools.getSysClipboardText();
                    if (Reply.NEW_DEFAULT_REPLY.equals(sysClipboardText)) {
                        robot.keyPress(KeyEvent.VK_CONTROL);
                        robot.delay(50);
                        robot.keyPress(KeyEvent.VK_1);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_1);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_CONTROL);
                    } else {
                        robot.keyPress(KeyEvent.VK_CONTROL);
                        robot.delay(50);
                        robot.keyPress(KeyEvent.VK_F8);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_F8);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_CONTROL);
                        robot.delay(50);
                        robot.mouseMove(735, 255);
                        RobotTools.setSysClipboardText(Reply.NEW_DEFAULT_REPLY);
                        robot.delay(50);
                        robot.mouseMove(950, 550);
                        robot.delay(50);
                        robot.mousePress(KeyEvent.BUTTON1_MASK);
                        robot.delay(50);
                        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                        robot.delay(50);
                        robot.keyPress(KeyEvent.VK_CONTROL);
                        robot.delay(50);
                        robot.keyPress(KeyEvent.VK_V);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_V);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_CONTROL);
                        robot.delay(50);
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_ENTER);
                        AutoReplyController.replyCount++;
                    }
                    robot.delay(200);
                    AutoReplyController.executionsCount++;
                }
            } catch (Exception exception) {
                emailSender.sendEmail("484201132@qq.com", "错误警告", "自动回复程序已发生严重错误，请立即联系技术部代毅处理，异常信息：\n\n" + getExceptionMessage(exception));
            }
        }).start();

        return 200;
    }

    @GetMapping("/stopAutoReplyTool")
    public int stopAutoReplyTool() {
        AutoReplyController.isRun = false;
        return 200;
    }


    @GetMapping("/getExecutionsCount")
    public int getExecutionsCount() {
        return AutoReplyController.executionsCount;
    }

    @GetMapping("/getReplyCount")
    public int getReplyCount() {
        return AutoReplyController.replyCount;
    }

    @GetMapping("/getIsRun")
    public boolean getIsRun() {
        return AutoReplyController.isRun;
    }

    private String getExceptionMessage(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        e.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        return stringWriter.toString();
    }

}
