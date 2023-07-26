const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {

    $scope.cart = {
        items: [],

        //Thêm sản phẩm vào giỏ
        add(id) {
            var item = this.items.find(item => item.id == id);
            // console.log(item)
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`)
                    .then(response => {
                        response.data.qty = 1;//Đặt số lượng  = 1
                        this.items.push(response.data);
                        this.saveToLocalStorage();
                    })
            }
        },

        //Xoá sản phẩm khỏi giỏ
        remove(id) {
            var index = this.items.findIndex(item => item.id === id)
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        clear() {
            this.items = [];
            this.saveToLocalStorage();

        },

        //Tính thành tiền 1 sản phẩm
        amt_of(item) {

        },

        //Tính tổng số mặt hàng
        get count() {
            return this.items.map(item => item.qty)
                .reduce((total, qty) => total += qty, 0)
        },

        //Tính tổng tiền các mặt hàng trong giỏ
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0)
        },

        //Lưu tạm thời dữ liệu
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            // console.log(json)
            localStorage.setItem("cart", json);
            // console.log(localStorage)
        },

        //Đọc giỏ hàng từ local storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            // console.log(json)
            this.items = json ? JSON.parse(json) : [];
        }

    }
    $scope.cart.loadFromLocalStorage();
    $scope.order = {
        createDate: new Date(),
        address: "",
        account: {
            username: $("#username").text(),
        },
        get orderDetails() {
            return $scope.cart.items.map(item => {
                    return {
                        product: { id: item.id },
                        price: item.price,
                        quantity: item.qty
                    }
                }
            )
        },
        purchaser() {
            // console.log(this.saveToLocalStorage())
            console.log($scope.cart.count)
            if(this.address.trim() === ""){
                alert("Vui lòng nhập địa chỉ");
            } else if($scope.cart.count === 0){
                alert("Vui lòng chọn sản phẩm trước thanh toán");
            }
            else {
                var order = angular.copy(this)
                console.log(order);
                $http.post('/rest/orders', order)
                    .then(response => {
                        alert('Đặt hàng thành công');
                        $scope.cart.clear();
                        location.href = "/order/detail/" + response.data.id;
                    })
                    .catch(error => {
                        alert('Đặt hàng lỗi');
                        console.log(error);
                    })
            }
        }
    }
})