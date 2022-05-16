let dataImageUploadEdit = [];
let dataImageUploadAdd = [];
let sizeImage = 0;

window.onload = function () {
    $('.modal').hide();
    CKEDITOR.replace('productAddDescription');
    CKEDITOR.replace('productEditDescription');
    $('#ip-edit-product-image').change(function () {
        for (var i = 0, f; (f = this.files[i]); i++) {
            if (!f.type.match("image.*")) {
                continue;
            }
            var reader = new FileReader();
            reader.onload = (function (theFile) {
                return function (e) {
                    dataImageUploadEdit.push(e.target.result)
                    addImagePreview(e.target.result)
                };
            })(f);
            // Read in the image file as a data URL.
            reader.readAsDataURL(f);
        }
    })
    $('#ip-add-product-image').change(function () {
        for (var i = 0, f; (f = this.files[i]); i++) {
            if (!f.type.match("image.*")) {
                continue;
            }
            var reader = new FileReader();
            reader.onload = (function (theFile) {
                return function (e) {
                    dataImageUploadAdd.push(e.target.result)
                    addImagePreviewAdd(e.target.result)
                };
            })(f);
            // Read in the image file as a data URL.
            reader.readAsDataURL(f);
        }
    })
    showAttributeCategory();
}

function showAttributeCategory() {
    let category = document.getElementById('ip-add-product-category');
    if (category !== null && category !== undefined) onChangeCategory(category);
}

function zoomImageProduct(e) {
    let srcImg = e.src
    console.log(srcImg)
    $('#modal-zoom-image').modal('show');
    let imgZoomImage = document.getElementById('img-zoom-image')
    if (imgZoomImage !== null && imgZoomImage !== undefined) {
        imgZoomImage.src = srcImg
    }
}

function onClickChooseImage() {
    $('#ip-edit-product-image').click()
}

function onClickChooseImageAdd() {
    $('#ip-add-product-image').click()
}

function addImagePreviewAdd(image) {
    let carouselIndicators = document.getElementById('carousel-indicators-add')
    if (sizeImage === 0) {
        carouselIndicators.innerHTML +=
            '<button type="button" data-bs-target="#carouselExampleCaptionsAdd" data-bs-slide-to="' + sizeImage + '"\n' +
            'aria-label="Slide ' + sizeImage + '" class="active" aria-current="true"></button>'
    } else {
        carouselIndicators.innerHTML +=
            '<button type="button" data-bs-target="#carouselExampleCaptionsAdd" data-bs-slide-to="' + sizeImage + '"\n' +
            'aria-label="Slide ' + sizeImage + '"></button>'
    }
    let divImage = document.createElement('div')
    divImage.className = sizeImage == 0 ? 'carousel-item active' : 'carousel-item'
    divImage.innerHTML = [
        '<img style="height: 600px;" src="',
        image,
        '" title="',
        '"/>'
    ].join("");
    document.getElementById("previewUploadAdd").insertBefore(divImage, null);
    sizeImage += 1;
}

function addImagePreview(image) {
    let carouselIndicators = document.getElementById('carousel-indicators')
    if (sizeImage === 0) {
        carouselIndicators.innerHTML +=
            '<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="' + sizeImage + '"\n' +
            'aria-label="Slide ' + sizeImage + '" class="active" aria-current="true"></button>'
    } else {
        carouselIndicators.innerHTML +=
            '<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="' + sizeImage + '"\n' +
            'aria-label="Slide ' + sizeImage + '"></button>'
    }
    let divImage = document.createElement('div')
    divImage.className = sizeImage == 0 ? 'carousel-item active' : 'carousel-item'
    divImage.innerHTML = [
        '<img style="height: 600px;" src="',
        image,
        '" title="',
        '"/>'
    ].join("");
    document.getElementById("previewUpload").insertBefore(divImage, null);
    sizeImage += 1;
}

function onClickUpdateProduct(e) {
    // get element mã sản phẩm
    let getElementProductCode = document.getElementById('ip-edit-product-code');
    // get element tên sản phẩm
    let getElementProductName = document.getElementById('ip-edit-product-name');
    // get element giá bán
    let getElementProductPrice = document.getElementById('ip-edit-product-price');
    // get element thương hiệu
    let getElementProduuctBrand = document.getElementById('ip-edit-product-brand');
    // get element trạng thái kinh doanh
    let getElementProductStatus = document.getElementById('ip-edit-product-status');
    // get element danh mục sản phẩm
    let getElementProductCategory = document.getElementById('ip-edit-product-category');
    // get element thuộc tính sản phẩm
    let getElementProductProperty = document.getElementById('product-edit-property');
    // get mô tả chi tiết sản phẩm
    let productDescription = CKEDITOR.instances['productEditDescription'].getData();
    let objectProduct = validateObjectProduct(
        getElementProductCode,
        getElementProductName,
        getElementProductPrice,
        getElementProduuctBrand,
        getElementProductStatus,
        getElementProductCategory,
        productDescription,
        dataImageUploadEdit,
        getElementProductProperty,
    );
    console.log(objectProduct);
    if (objectProduct !== null && objectProduct !== undefined) {
        objectProduct["productId"] = e.dataset.id
        $.ajax({
            url: '/api/product',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(objectProduct),
            success: function (data) {
                console.log(data);
                toastSuccess('Thành công', 'Đã cập nhật sản phẩm')
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                toastDanger('Lỗi', jqXHR.responseJSON.vn);
            }
        })
    }
}

function onClickSaveProduct() {
    // get element tên sản phẩm
    let getElementProductName = document.getElementById('ip-add-product-name');

    // get element he dieu hanh
    let getElementProductOs = document.getElementById('ip-add-product-os');

    // get element man hinh
    let getElementProductScreen = document.getElementById('ip-add-product-screen');

    // get element chip
    let getElementProductChip = document.getElementById('ip-add-product-chip');

    //get element cam truoc
    let getElementProductCamTruoc = document.getElementById('ip-add-product-cam_truoc')

    //get element cam sau
    let getElementProductCamSau = document.getElementById('ip-add-product-cam_sau')

    //get element sim
    let getElementProductSim = document.getElementById('ip-add-product-sim')

    //get element pin sac
    let getElementProductPin = document.getElementById('ip-add-product-pin')

    // get mô tả chi tiết sản phẩm
    let productDescription = CKEDITOR.instances['productAddDescription'].getData();

    // get element danh muc
    let productCategory = document.getElementById('ip-add-product-category').value;

    // get element ram
    let productRam = document.getElementById('ip-add-product-ram').value;

    // get element rom
    let formRom8Gb = {};
    let formRom32Gb = {};
    let formRom64Gb = {};
    let formRom16Gb = {};
    let formRom128Gb = {};
    let formRom256Gb = {};
    let formRom512Gb = {};
    let romRequestAdds = [];
    let check = 0
    for (let i = 0; i <= 6; i++) {
        if(document.getElementById('rom' + i).checked && i === 0){
            formRom8Gb = {
                "nameRom" : document.getElementById('rom' + i).value
            }
            romRequestAdds.push(formRom8Gb)
            check++;
        }
        if(document.getElementById('rom' + i).checked && i === 1){
            formRom16Gb = {
                "nameRom" : document.getElementById('rom' + i).value
            }
            romRequestAdds.push(formRom16Gb)

            check++;
        }
        if(document.getElementById('rom' + i).checked && i === 2){
            formRom32Gb = {
                "nameRom" : document.getElementById('rom' + i).value

            }
            romRequestAdds.push(formRom32Gb)

            check++;
        }
        if(document.getElementById('rom' + i).checked && i === 3){
            formRom64Gb = {
                "nameRom" : document.getElementById('rom' + i).value

            }
            romRequestAdds.push(formRom64Gb)

            check++;
        }
        if(document.getElementById('rom' + i).checked && i === 4){
            formRom128Gb = {
                "nameRom" : document.getElementById('rom' + i).value

            }
            check++;
            romRequestAdds.push(formRom128Gb)

        }
        if(document.getElementById('rom' + i).checked && i === 5){
            formRom256Gb = {
                "nameRom" : document.getElementById('rom' + i).value

            }
            romRequestAdds.push(formRom256Gb)

            check++;
        }
        if(document.getElementById('rom' + i).checked && i === 6){
            formRom512Gb = {
                "nameRom" : document.getElementById('rom' + i).value

            }
            romRequestAdds.push(formRom512Gb)

            check++;
        }
    }
    if(check > 0){
        console.log(romRequestAdds)
    }

    let objectProduct = validateObjectProduct(
        getElementProductName,
        getElementProductOs,
        getElementProductScreen,
        getElementProductCamSau,
        getElementProductCamTruoc,
        getElementProductPin,
        getElementProductChip,
        getElementProductSim,
        productCategory,
        productRam,
        productDescription,
        dataImageUploadAdd,
        check,
        romRequestAdds
    );
    console.log(objectProduct);
    if (objectProduct !== null && objectProduct !== undefined) {
        $.ajax({
            url: '/api/product',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(objectProduct),
            success: function (data) {
                toastSuccess('Thành công', 'Đã lưu mới sản phẩm')
                console.log(data);
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                toastDanger('Lỗi', jqXHR.responseJSON.vn);
            }
        })
    }






}
function onRoleChange() {
    const role = $('select.roleFilter').val();
    const rows = $('table#datatable tr');

    if (role.length === 0) {
        rows.show();
    } else {
        rows.hide();
        role.forEach(e => {
            rows.find(`.role:contains(${e})`).parent().parent().show();
        })
    }
    resizeTable();
}
function validateObjectProduct(
    getElementProductName,
    getElementProductOs,
    getElementProductScreen,
    getElementProductCamSau,
    getElementProductCamTruoc,
    getElementProductPin,
    getElementProductChip,
    getElementProductSim,
    productCategory,
    productRam,
    productDescription,
    productImages,
    check,
    romRequestAdds
) {
    let productOs, productName, productScreen, productCamSau,
        productChip, productPin, productCamTruoc, productSim;

    // get tên sản phẩm
    if (getElementProductName !== null && getElementProductName !== undefined) {
        productName = getElementProductName.value;
    }
    // get os
    if (getElementProductOs !== null && getElementProductOs !== undefined) {
        productOs = getElementProductOs.value;
    }
    // get screen
    if (getElementProductScreen !== null && getElementProductScreen !== undefined) {
        productScreen = getElementProductScreen.value;
    }
    // get cam sau
    if (getElementProductCamSau !== null && getElementProductCamSau !== undefined) {
        productCamSau = getElementProductCamSau.value
    }
    // get cam truoc
    if (getElementProductCamTruoc !== null && getElementProductCamTruoc !== undefined) {
        productCamTruoc = getElementProductCamTruoc.value;
    }

    // get pin
    if (getElementProductPin !== null && getElementProductPin !== undefined) {
        productPin = getElementProductPin.value;
    }

    // get chip
    if (getElementProductChip !== null && getElementProductChip !== undefined) {
        productChip = getElementProductChip.value;
    }


    // get sim
    if (getElementProductSim !== null && getElementProductSim !== undefined) {
        productSim= getElementProductSim.value;
    }


    // get mô tả chi tiết sản phẩm
    if (productDescription !== null && productDescription !== undefined) {
        productDescription = (productDescription + '').substring(3);
        productDescription = (productDescription + '').substring(0, productDescription.length - 5);
    }



    // validate tên sản phẩm
    if (productName === null || productName === undefined || productName === '') {
        console.log('Vui lòng nhập tên sản phẩm');
        toastDanger('Lỗi', 'Vui lòng nhập tên sản phẩm');
        return;
    }
    if (productName.length < 10 || productName.length > 100) {
        console.log('Tên sản phẩm chỉ từ 10 đến 100 ký tự');
        toastDanger('Lỗi', 'Tên sản phẩm chỉ từ 10 đến 100 ký tự');
        return;
    }

    // validate hệ điều hành
    if (productOs === null || productOs === undefined || productOs === '') {
        console.log('Vui lòng nhập hệ điều hành');
        toastDanger('Lỗi', 'Vui lòng nhập hệ điều hành');
        return;
    }

    // validate screen
    if (productScreen === null || productScreen === undefined || productScreen === '') {
        console.log('Vui lòng nhập loại màn hình');
        toastDanger('Lỗi', 'Vui lòng nhập loại màn hình');
        return;
    }

    // validate cam trước
    if (productCamTruoc === null || productCamTruoc === undefined || productCamTruoc === '') {
        console.log('Vui lòng nhập dạng cam trước');
        toastDanger('Lỗi', 'Vui lòng nhập dạng cam trước');
        return;
    }

    // validate cam sau
    if (productCamSau === null || productCamSau === undefined || productCamSau === '') {
        console.log('Vui lòng nhập dạng cam sau');
        toastDanger('Lỗi', 'Vui lòng nhập dạng cam sau');
        return;
    }
    // validate chip
    if (productChip === null || productChip === undefined || productChip === '') {
        console.log('Vui lòng nhập dạng chip');
        toastDanger('Lỗi', 'Vui lòng nhập dạng chip');
        return;
    }
    // validate pin
    if (productPin === null || productPin === undefined || productPin === '') {
        console.log('Vui lòng nhập dạng pin');
        toastDanger('Lỗi', 'Vui lòng nhập dạng pin');
        return;
    }

    // validate sim
    if (productSim === null || productSim === undefined || productSim === '') {
        console.log('Vui lòng nhập dạng sim');
        toastDanger('Lỗi', 'Vui lòng nhập dạng sim');
        return;
    }

    // validate loại sản phẩm & thuộc tính sản phẩm
    if (productCategory === null || productCategory === undefined || productCategory === '') {
        console.log('Vui lòng chọn danh mục sản phẩm');
        toastDanger('Lỗi', 'Vui lòng chọn danh mục sản phẩm');
        return;
    }

    // validate mô tả chi tiết sản phẩm
    if (productDescription === null || productDescription === undefined || productDescription === '') {
        console.log('Vui lòng nhập mô tả chi tiết sản phẩm');
        toastDanger('Lỗi', 'Vui lòng nhập mô tả chi tiết sản phẩm');
        return;
    }

    // validate hình ảnh sản phẩm
    if (productImages === null || productImages === undefined || productImages.length === 0) {
        console.log('Vui lòng chọn hình ảnh sản phẩm');
        toastDanger('Lỗi', 'Vui lòng chọn hình ảnh sản phẩm');
        return;
    }

    if(check === 0){
        console.log('Vui lòng chọn dung lượng');
        toastDanger('Lỗi', 'Vui lòng chọn dung lượng');
        return;
    }




    return {
        "nameProduct": productName,
        "categoryId" : productCategory,
        "note": productDescription,
        "image": productImages,
        "attributeRequestAdd": {
            "operatingSystem" : productOs,
            "screen" : productScreen,
            "chip" : productChip,
            "camTruoc" : productCamTruoc,
            "camSau" : productCamSau,
            "sim" : productSim,
            "pin" : productPin,
            "ram" : productRam
        },
        romRequestAdds
    };
}



function onClickAddProduct() {
    dataImageUploadEdit = [];
}

function onClickEditProduct(e) {
    dataImageUploadEdit = [];
    let id = e.dataset.id;
    // get element mã sản phẩm
    let getElementButtonEditProduct = document.getElementById('btn-submit-edit-product');
    if (getElementButtonEditProduct !== null) {
        getElementButtonEditProduct.dataset.id = id
    }

    // get element tên sản phẩm
    let getElementProductName = document.getElementById('ip-edit-product-name');

    // get element he dieu hanh
    let getElementProductOs = document.getElementById('ip-edit-product-os');

    // get element man hinh
    let getElementProductScreen = document.getElementById('ip-edit-product-screen');

    // get element chip
    let getElementProductChip = document.getElementById('ip-edit-product-chip');

    //get element cam truoc
    let getElementProductCamTruoc = document.getElementById('ip-edit-product-cam_truoc')

    //get element cam sau
    let getElementProductCamSau = document.getElementById('ip-edit-product-cam_sau')

    //get element sim
    let getElementProductSim = document.getElementById('ip-edit-product-sim')

    //get element pin sac
    let getElementProductPin = document.getElementById('ip-edit-product-pin')

    // get mô tả chi tiết sản phẩm
    let productDescription = CKEDITOR.instances['productAddDescription'].getData();

    // get element danh muc
    let productCategory = document.getElementById('ip-edit-product-category');

    // get element ram
    let productRam = document.getElementById('ip-edit-product-ram');
    $.ajax({
        url: '/api/product/' + id,
        method: 'GET',
        success: function (data) {
            console.log(data)
            if (getElementProductCode !== null) {
                getElementProductCode.value = data.productCode;
            }
            if (getElementProductName !== null) {
                getElementProductName.value = data.productName;
            }
            if (getElementProductPrice !== null) {
                getElementProductPrice.value = data.productPrice;
            }
            if (getElementProduuctBrand !== null) {
                getElementProduuctBrand.value = data.productBrand;
            }
            if (getElementProductStatus !== null) {
                getElementProductStatus.checked = data.productStatus === 'ON';
            }
            if (data.lstImages !== null && data.lstImages !== undefined) {
                dataImageUploadEdit = data.lstImages
                sizeImage = 0;
                let previewUpload = document.getElementById('previewUpload')
                let carouselIndicators = document.getElementById('carousel-indicators')
                if (previewUpload !== null && previewUpload !== undefined) {
                    previewUpload.innerHTML = ''
                    carouselIndicators.innerHTML = ''
                }
                for (let i = 0; i < data.lstImages.length; i++) {
                    addImagePreview(data.lstImages[i])
                }
            }
            CKEDITOR.instances['productEditDescription'].setData(data.productDescription)
            if (getElementProductCategory !== null) {
                getElementProductCategory.value = data.productCategory;
                getElementProductCategory.dataset.id = id
                $('#ip-edit-product-category').change();
                $.ajax({
                    url: '/product/property/edit?category=' + data.productCategory + '&product=' + id,
                    method: 'GET',
                    success: function (html) {
                        if (getElementProductProperty !== null) {
                            getElementProductProperty.innerHTML = html;
                        }
                    }
                })
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR)
        }
    })
}

function onStatusChange(element) {
    const value = $(element).val();
    const tagAll = $('#datatable tbody tr');
    const tag = $('tbody tr td span').filter(function () {
        return $(this).text().includes(value);
    });
    if (value === '') {
        tagAll.show();
    } else {
        tagAll.hide();
        tag.parent().parent().show();
    }
}

function onClickCheckAllProductAdd(e) {
    let getChk = document.getElementsByClassName('chk-add-product')
    for (const chk of getChk) {
        if (!chk.parentElement.parentElement.hidden) {
            chk.checked = e.checked
        }
    }
}

function onClickCheckProductAdd(e) {
    if (!e.checked) {
        let getChkAll = document.getElementById('chk-add-all-product');
        if (getChkAll !== null && getChkAll !== undefined) {
            getChkAll.checked = false;
        }
    } else {
        enableCheckBox();
    }
}

function enableCheckBox() {
    let getChkAll = document.getElementById('chk-add-all-product');
    let countChecked = 0;
    let countChk = 0;
    let getChk = document.getElementsByClassName('chk-add-product')
    for (const chk of getChk) {
        if (!chk.hidden && !chk.parentElement.parentElement.hidden) {
            countChk++;
            if (chk.checked) countChecked++;
        }
    }
    if (getChkAll !== null && getChkAll !== undefined)
        getChkAll.checked = countChecked === countChk
}

function onClickOrderInvoice() {
    $('.tr-order-add-product').hide();
    $('.tr-order-title').hide();
    $('.quantity-product').val(0);
    let count = 0;
    $('.chk-add-product').each((index, product) => {
        if (product.checked) {
            count++;
            // $('.tr-order-add-product').show();
            // console.log($('.tr-order-add-product').show())
            // console.log($('.tr-order-add-product[data-id-product="' + product.id + '"]'))
            // console.log($('.tr-order-add-product[data-id-product="' + product.id + '"]').show())
            $('.tr-order-add-product[data-id-product="' + product.id + '"]').show()
        }
    })
    if (count === 0) {
        $('.tr-order-add-product[data-top="true"]').show();
        $('.tr-order-title').show();
    }
}


function onChangeQuantity(e) {
    let value = e.value;
    if (value === null || value === undefined || value === '' || !Number(value) || Number(value) < 0) {
        e.value = 0;
    } else if (Number(value) < Number(e.dataset.min)) {
        e.value = Number(e.dataset.min);
    } else {
        e.value = Number(value);
    }
}

function onClickSubmitAddOrderInvoice() {
    let getOrderCode = document.getElementById('ip-order-invoice-code');
    let orderCode;
    if (getOrderCode !== null && getOrderCode !== undefined) {
        orderCode = getOrderCode.value;
        if (orderCode === null || orderCode === undefined || orderCode === '') {
            toastDanger('Lỗi', 'Vui lòng nhập mã đặt hàng');
            return;
        }
        if (orderCode.length < 5 || orderCode.length > 50) {
            toastDanger('Lỗi', 'Mã đặt hàng từ 5 đến 50 ký tự');
            return;
        }
    }
    let getDate = document.getElementById('ip-order-invoice-date');
    let orderDate;
    if (getDate !== null && getDate !== undefined) {
        orderDate = getDate.value;
        if (orderDate === null || orderDate === undefined || orderDate === '') {
            toastDanger('Lỗi', 'Vui lòng chọn ngày hẹn nhận');
            return;
        }
    }
    let getSupplier = document.getElementById('ip-order-invoice-supplier');
    let idSupplier;
    if (getSupplier !== null && getSupplier !== undefined) {
        idSupplier = getSupplier.value;
        if (idSupplier === null || idSupplier === undefined) {
            toastDanger('Lỗi', 'Vui long chọn nhà cung cấp');
            return;
        }
    }
    let getNote = document.getElementById('ip-order-invoice-note');
    let note;
    if (getNote !== null && getNote !== undefined) {
        note = getNote.value;
    }
    let lstDetails = [];
    let getTrProduct = document.getElementsByClassName('tr-order-add-product');
    if (getTrProduct !== null && getTrProduct !== undefined) {
        for (const $tr of getTrProduct) {
            let idProduct = $tr.dataset.id;
            let quantity;
            let getQuantity = $tr.getElementsByClassName('quantity-product');
            if (getQuantity !== null && getQuantity !== undefined) {
                quantity = getQuantity[0].value;
                if (quantity === null || quantity === undefined || !Number(quantity) || Number(quantity) < 1) {
                    // toastDanger('Lỗi', 'Vui lòng nhập số lượng đặt lớn hơn 0');
                    // return;
                } else {
                    lstDetails.push({
                        "productId": idProduct,
                        "quantity": quantity
                    })
                }
            }
        }
    }
    if (lstDetails.length === 0) {
        toastDanger('Lỗi', 'Vui lòng nhập số lượng ít nhất 1 sản phẩm');
        return;
    }
    let obj = {
        "code": orderCode,
        "date": orderDate,
        "supplierId": idSupplier,
        "note": note,
        "details": lstDetails
    }
    $.ajax({
        url: '/api/order-invoice',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (data) {
            toastSuccess('Thành công', 'Đã tạo mới hoá đơn đặt hàng NCC');
            $('.btn-close').click();
        },
        error: function (error) {
            if (error.responseJSON.vn === null || error.responseJSON.vn === undefined) {
                let message = error.responseJSON.message + '';
                message = message.substring(message.indexOf(':') + 1)
                toastDanger('Lỗi', message);
                return;
            }
            toastDanger('Lỗi', error.responseJSON.vn);
        }
    })
    console.log(obj);
}