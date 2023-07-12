package nom.youcanwell.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class OrderDto {
    @Getter
    @ToString
    public static class getTid{
        private String tid;
    }

    @Getter
    @Setter
    @ToString
    public static class OrderReadyResponse {

        private String tid;
        private String next_redirect_pc_url;
        private String created_at;
    }

    @Getter
    @Setter
    @ToString
    public static class ApproveResponse {
        private String aid;
        private String tid;
        private String cid;
        private String sid;
        private String partner_order_id;
        private String partner_user_id;
        private String payment_method_type;
        private String item_name;
        private String item_code;
        private int quantity;
        private String created_at;
        private String approved_at;
        private String payload;
        private Amount amount;

    }

    @Getter
    @Setter
    @ToString
    public static class Amount {

        private int total;
        private int tax_free;
        private int tax;
        private int point;
        private int discount;
    }

    @Getter
    @Setter
    public static class OrderPostinfo{
        private String ItemName;
        private int quantity;
        private double price;
        private int orderTax;

    }
}
