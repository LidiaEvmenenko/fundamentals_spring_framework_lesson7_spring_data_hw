angular.module('app-front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1/';

let pageInt=1;
let pageMax=0;
let pageElements=0;
let totalElementsEmpty=false;
let currentPageIndex = 1;
let inputId;
let inputTitle;
let inputPrice;
let inputCategoryTitle;

    $scope.loadProducts = function(pageIndex = 1){
         currentPageIndex = pageIndex;
          $http({
             url: contextPath + 'products',
             method: 'GET',
             params: {
              p: pageIndex
             }
          }).then(function(response) {
             console.log(response);
             $scope.productsPage = response.data;
             $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
          });
          }

    $scope.loadProducts();

    $scope.deleteProduct = function (product) {
     $http.delete(contextPath + 'products/'+ product.id)
                 .then(function successCallback(response) {
                     $scope.productsPage = response.data;
                     pageElements = pageElements - 1;
                     if(pageElements == 0 && pageInt != 1) {
                        pageInt = pageInt - 1;
                     }
                     else{
                        if(pageElements == 0 && pageInt == 1) {
                           totalElementsEmpty = true;
                           }
                     }
                     $scope.loadProducts(currentPageIndex);
                     }, function failCallback(response) {
                               alert(response.data.put_product);
                    });
    }

    $scope.putFormProduct = function (product) {
                             inputId = document.getElementById('id');
                             inputTitle = document.getElementById('title');
                             inputPrice = document.getElementById('price');
                             inputCategoryTitle = document.getElementById('categoryTitle');
                             inputId.value = product.id;
                             inputTitle.value = product.title;
                             inputPrice.value = product.price;
                             inputCategoryTitle.value = product.categoryTitle;
                 }

     $scope.deleteAll = function () {
          $http.delete(contextPath + 'products')
                      .then(function successCallback(response) {
                          $scope.loadProducts();
                          }, function failCallback(response) {
                            alert(response.data.put_product);
                      });

         }

     $scope.showInfo=function(product){
        alert("Категория: " + product.categoryTitle);
        }

     $scope.createNewProduct = function () {
                $http.post(contextPath + 'products', $scope.new_product)
                    .then(function successCallback(response) {
                            $scope.loadProducts(currentPageIndex);
                            $scope.new_product = null;
                        }, function failCallback(response) {
                            alert(response.data.message);
                        }
                    );
            }

    $scope.putProduct = function () {
         inputId = document.getElementById('id');
                             inputTitle = document.getElementById('title');
                             inputPrice = document.getElementById('price');
                             inputCategoryTitle = document.getElementById('categoryTitle');
                             $scope.put_product.id = inputId.value;
                             $scope.put_product.title = inputTitle.value;

        $scope.put_product.price = inputPrice.value;
        $scope.put_product.categoryTitle = inputCategoryTitle.value;
                $http.put(contextPath + 'products', $scope.put_product)
                    .then(function successCallback(response) {
                            $scope.loadProducts(currentPageIndex);
                            $scope.put_product = null;

                             inputId.value = '';
                             inputTitle.value = null;
                             inputPrice.value = null;
                             inputCategoryTitle.value = null;
                        }, function failCallback(response) {
                            alert(response.data.put_product);
                        }
                    );
            }

     $scope.generatePagesIndexes = function (startPage, endPage) {
                    let arr = [];
                    for (let i = startPage; i < endPage + 1; i++) {
                        arr.push(i);
                    }
                    return arr;
                }

     $scope.nextPage = function () {
                    currentPageIndex++;
                    if (currentPageIndex > $scope.productsPage.totalPages) {
                        currentPageIndex = $scope.productsPage.totalPages;
                    }
                    $scope.loadProducts(currentPageIndex);
                }

     $scope.prevPage = function () {
                    currentPageIndex--;
                    if (currentPageIndex < 1) {
                        currentPageIndex = 1;
                    }
                    $scope.loadProducts(currentPageIndex);
                }
});