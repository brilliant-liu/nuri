package icop.test.queue.design.factory;

/**
 * @author: liukj
 * @date: 2020/8/13
 * @description：
 */
public class FactoryMethod {

    public void main(String[] args) {
        MessageFactory messageFactory = new WeChatMassageFactory();
        MessageFactory SMSFactory = new SMSMessageFactory();
        Send weChatSender = messageFactory.produce();
        Send SMSSender = SMSFactory.produce();
        weChatSender.sendMessage();
        SMSSender.sendMessage();
    }




    public class WeChat implements Send {
        String message;

        public WeChat(String message) {
            this.message = message;
        }

        @Override
        public void sendMessage() {
            System.out.println("发送信息："+this.message);
        }
    }

    public class SMS implements Send{
        String message;
        String type;

        public SMS(String message,String type) {
            this.message = message;
            this.type =type;
        }

        @Override
        public void sendMessage() {
            System.out.println("SMS发送["+this.type+"]信息："+this.message);
        }
    }


    public  class WeChatMassageFactory implements MessageFactory {

        @Override
        public Send produce() {
            return new WeChat("微信。。。。");
        }
    }

    public class SMSMessageFactory implements MessageFactory{

        @Override
        public Send produce() {
            return new SMS("SMS。。。","SMS");
        }
    }




}

