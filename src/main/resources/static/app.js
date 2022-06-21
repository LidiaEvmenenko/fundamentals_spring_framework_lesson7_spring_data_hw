angular.module('app-front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/';

let pageInt=1;
let pageMax=0;
let pageElements=0;
let totalElementsEmpty=false;

    $scope.loadProducts = function(){
     // if(!totalElementsEmpty){
      $http.get(contextPath + 'products?page='+pageInt)
      .then(function(response) {
         console.log(response);
         $scope.productsPage = response.data;
         pageMax=response.data.totalPages;//сколько всего страниц
         console.log('pageMax = '+pageMax);
         pageElements=response.data.numberOfElements;//сколько записей на данной странице
         console.log('pageElements = '+pageElements);
         console.log('totalElementsEmpty = '+totalElementsEmpty);
          console.log('pageInt = '+pageInt);
      });
    //  }
    }

    $scope.loadProducts(); //вызов функции

     $scope.loadProductsBefore = function () {
        if(!totalElementsEmpty){
            pageInt=pageInt - 1;
            if(pageInt == 0){pageInt=1;}
            console.log('pageInt = '+pageInt);
             $http.get(contextPath + 'products?page='+pageInt)
                  .then(function(response) {
                  console.log(response);
                  $scope.productsPage = response.data;
                   console.log('pageInt = '+pageInt);
                   pageElements=response.data.numberOfElements;//сколько записей на данной странице
                   console.log('pageElements = '+pageElements);
                  });
                  }
        }
         $scope.loadProductsAfter = function () {
             if(!totalElementsEmpty){
                   pageInt=pageInt + 1;
                   if(pageInt > pageMax) { pageInt = pageMax; }
                   console.log('pageInt = '+pageInt);
                   $http.get(contextPath + 'products?page='+pageInt)
                       .then(function(response) {
                          console.log(response);
                          $scope.productsPage = response.data;
                          pageElements=response.data.numberOfElements;//сколько записей на данной странице
                          console.log('pageElements = '+pageElements);
                          });
             }
         }

    $scope.deleteProduct = function (product) {
     $http.get(contextPath + 'products/delete/'+ product.id)
                 .then(function (response) {
                     console.log(response);
                     $scope.productsPage = response.data;
                     pageElements = pageElements - 1;
                     console.log('pageElements = '+pageElements);
                     if(pageElements == 0 && pageInt != 1) {
                        pageInt = pageInt - 1;
                     }
                     else{
                        if(pageElements == 0 && pageInt == 1) {
                           totalElementsEmpty = true;
                           console.log('totalElementsEmpty = '+totalElementsEmpty);
                           }
                     }
                     console.log('pageInt = '+pageInt);
                     $scope.loadProducts();
                 });
    }
});