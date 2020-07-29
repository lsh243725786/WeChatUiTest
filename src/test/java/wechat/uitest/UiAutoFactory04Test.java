package wechat.uitest;

import org.junit.jupiter.api.Test;

class UiAutoFactory04Test {

    @Test
    void create() {
        BasePage_01 web = UiAutoFactory_04.create("web");
        UiAuto_02 uiAuto_02 = web.load("/wechatuitest/webuiauto.yaml");
        web.run(uiAuto_02);
    }
}