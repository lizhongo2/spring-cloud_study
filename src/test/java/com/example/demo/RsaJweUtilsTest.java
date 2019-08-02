package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/*
  Created by IntelliJ IDEA.  
  User: Shaofei Du.  
  Date: 2019/8/1 
*/
public class RsaJweUtilsTest {

    @Test
    public void testRsaJwe() {

        String base64PublicKey = "87qwfIICT4uTNGI3wp9Ctik0fjz5aDIg/uVHtbhzH4hwOvXkI4qxYBfYdpnqgmh5oJZy96/U/I3p8lzDJJz16mqAUD1ljk5B312KCdoU/1VQ5MoqBCXHqnsofKNIb05vf1uqyC36pt2qS45C2tdyZHX8WaMGIm8Qh60/uibNuga4Bd3C2J7tEW51lt1sQHzNJ5MepaHfGWcSsYgoD9tmzkJgtFF7EWn+5doW3ECIvLNqniR6bCFO6D9wNFdPVJhR/lkIsscFO2H+6Nf4+nyiaxpX5Qz6kGlN/OZbEgI+BVOcT5bDLmsxoHQEHUV3AGQhap0ImKBzI0YtmbnjIde01S3sP90hSlCtdx6UXT3hgl51z8Rgg3MVA0s9BHy54ShBNWwH8NZLRBDZKV/3wOf/cAcRuT4tJ//lT8AFqTs5MchDm+BrqOY3yO+g+k45SUSAJ99qxeZrTsvPdAGEzLpqpBmkXFqtifb3YLRMdcSZcYmx+VstntcvxTMqCZRGBf+pZJiyTVzLD10QOYtP3HQ2uiCcsaoOiaF/2kX6JmyIr/I=";
        String data = RsaJweUtils.encryptRsaJwe("{\"password\":\"Qq1111111\",\"timestamp\":\"1564646824234\"}", base64PublicKey);
        Assert.assertNotNull(data);
        String basePrivateKey = "87qwfIICT4uTNGI3wp9Ctik0fjz5aDIg/uVHtbhzH4hwOvXkI4qxYBfYdpnqgmh5oJZy96/U/I3p8lzDJJz16mqAUD1ljk5B312KCdoU/1VQ5MoqBCXHqnsofKNIb05vf1uqyC36pt2qS45C2tdyZHX8WaMGIm8Qh60/uibNuga4Bd3C2J7tEW51lt1sQHzNJ5MepaHfGWcSsYgoD9tmzkJgtFF7EWn+5doW3ECIvLNqniR6bCFO6D9wNFdPVJhR/lkIsscFO2H+6Nf4+nyiaxpX5Qz6kGlN/OZbEgI+BVOcT5bDLmsxoHQEHUV3AGQhap0ImKBzI0YtmbnjIde01S3sP90hSlCtdx6UXT3hgl51z8Rgg3MVA0s9BHy54ShBNWwH8NZLRBDZKV/3wOf/cAcRuT4tJ//lT8AFqTs5MchDm+BrqOY3yO+g+k45SUSAJ99qxeZrTsvPdAGEzLpqpBmkXFqtifb3YLRMdcSZcYmx+VstntcvxTMqCZRGBf+pZJiyTVzLD10QOYtP3HQ2uhfVCorLcuck5eZ3TDo+v3/NebQqFcrPlXFuw9CgW57vXUky5W8ayaywAYC7Irnm5UYV+5ujoynvl1tg23InyOwsF00aNj8a/iy6fTZp1vgmXMBOs01IYjZbsp2A7wKkY60y97S3z2n6Ddpp9gYeQoODPG4UqaEIVkvwueA4o62OeLzaRiD4AXORSw8yL9MEyeJu3sq7OSGZRHQyrug9KXYUAqD7dyI30uHY2JMLZY9u9Uk0lvVF5HVAOkLnvphZG8WMzRFtiz82EW3ogNtn4wJtmjCqN7cVqxe67/wV9xrFVezy8hrNElTskDTD/i2R6QpItb+nsb2Y3uQwmQ8/6hkv2lrt4Btc+Wi923qWRhn8iaKx5Rb0qmGFJsOMB6z3ggn0sq1isxjwUscgZ4OcjbR4F+rjoUgBY/YVU35edXWEsvC1vyStfiwLz6WeMxjoWeop/aqqLJjEZWwTBNbDzi8AEVrpooxd3OIh2t0xHiEFCSQam746yQOmDYX4BHNw65BZ/tC+B3+SiqIb38T5L8mMCnpikfkQed7Qa5w7cYj0zre0ItDLZb6LZ4cbPWjj9XkGIn0OgTQKZWe6IJeStEYSl0YsH1jaedRoQCX6J0YbgvZ0vxICzmQX/w9UQHQgvZjjYrR1d9EZzkUTaUcMxfKABu/fvn9Q2DFl5DsmUk528TWB4y6qQwRfH7IHpxtRJr6AWkk3uNm/hEG0whL9wJVTaFBrh710EfbrrQu0NqZNXs37R2+zUViPHqjKigDXQIW3Cep+U7FjN7WlOw+6ddz2oZOVJe+/cxQ55L5xQeGMJWgG7iAbGMCL+QIvbcWqJPz2yPZHdKP02XHJ216gSFYXXkWwoZAPfcelTwj+HkauqRwV4zsA7pHVIrrgocqkmNVrVeTmmNbos/svcRRbvrEIDwm8HLDHSFD+yd0ndoFxKim6MkMU8/2dvkr7SsxahuuhVEBmQO6PzmKgA8HFPG3/CcMmM7H/zmMZCJ9xMkVZ2yPONRhie/JEaZwjceFczHrO3ThnZBDtk6loctwhiHSTJXontJUeDRjB7cQoDeHUO0Fg6hf5RFe2w1VWM9dpmwYf++mZugsMkGoP28LA6sXLRkKSYoWu9gz8biPpZNuk+/a+mh11kT1+0giwluSX4JmOK5rlw7VrmXYW3hfwtJupXghpOPS63vifq42PeMVWd87TKLUJr4mbu/BbhuPA0ou9FgVsaSv6m1XTmmWy6SCmu6vqms7pyggatB/Du1G8HE2ZyR4wMR/aTZWJ34UF4elss6c6+jIM6+b/waNF6pl9AHD7tGMYtpUDC/HdbwiOSm1wOY0guJpLNQXyBUaaW15QV2m9z5ZgU7gIxY99uh61OSebikngNLbk3sxL7hTXTXsH5nDgLQagNyz8hbqADJ1u3xNVpt1bKEAzwjjxqnwluAG4uMoB8L1rzZeaEAad2+cchvbSfJn1OqRhwL29itbF9rI1xvDR7EvNd/OHAJzAzHeqURVUeQMGCt8/CSXJ6J27RGk/gcKuvz+oSx7NEBzcr+Ph31VD0KZuqyILDtU45HhkLWYMAris2PVlj/iw357oNP/mLXb1inhOKyJYULJlkgIPq44lDhqfT4IXIjbtRqh/jzo6stTqNA6gcuVDz5G66K8nkYxL59MaqEIMfDdcrxGOAroXPpQsM0QYPAc=";
        String rsaDecrypt = RsaJweUtils.rsaJweDecrypt(data, basePrivateKey);
        Assert.assertNotNull(rsaDecrypt);

        JSONObject jsonObject = JSONObject.parseObject(rsaDecrypt);
        Object password = jsonObject.get("password");
        Object timestamp = jsonObject.get("timestamp");

        Assert.assertNotNull(password);
        Assert.assertNotNull(timestamp);

        //默认密码有效期2分钟
        long currentTimeMillis = System.currentTimeMillis();

        long passwordTimeMillis = Long.valueOf(timestamp.toString());

        //默认有效期两分钟
        long check = 1000 * 60 * 2;

        if (currentTimeMillis - passwordTimeMillis > check) {
            Assert.fail("密码已过期.");
        }

        Assert.assertTrue(true);


        Assert.assertNotNull(password);

        System.out.println(password);
        System.out.println(rsaDecrypt);

    }

}
