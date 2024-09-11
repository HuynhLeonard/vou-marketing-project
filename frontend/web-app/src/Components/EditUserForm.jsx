/* eslint-disable jsx-a11y/anchor-is-valid */
import React, {useState} from "react";
import avatar1 from "../utils/images/avatar.png";
import { useMutation } from "react-query";
import { IoMdClose, IoIosArrowDown } from "react-icons/io";
import { useRef } from "react";


import {callApiUpdateAccount} from "../service/user"

// function EditUserForm({ currentProfile }) {

//     const [user, setUser] = useState(currentProfile);

//     const formAccount = useRef(null);


//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setUser(prev => ({ ...prev, [name]: value }));
//   };

//   const updateAccountMutation = useMutation(
//     ({id,data}) => callApiUpdateAccount(id,data),
//     {
//       onSuccess: (data) => {
//         console.log(data)
//         //handleClose();
//         //handleNoti(false,"Cập nhật thông tin thành công")
        
//       },
//       onError: (error) => {
//           const msgErr = error.response.data.message;
//           //setIsError(true);
//           //setShowNoti(true);
//           //setNotiMsg(msgErr);
//       },
//     }
//   )



//   const handleSaveChange = (e) => {
//     // handleClose();
//     e.preventDefault();
//     const formData = new FormData(formAccount.current);
//     const formProps = Object.fromEntries(formData);
//     console.log(formProps)

//     updateAccountMutation.mutate({id: user.idUser,data: formProps});
//   };

//     const confirmSave = (buttonSize) => {
//         var valueList = [];

//         if (buttonSize === "large") {
//             const inputList = [
//                 ".username-input",
//                 ".role-input",
//                 ".name-input",
//                 ".email-input",
//                 ".phone-input",
//                 ".gender-input",
//                 ".status-input",
//                 ".facebook-input",
//                 ".birth-input",
//                 ".ava-input",
//             ];

//             inputList.forEach((input) => {
//                 var inputValue = document.querySelector(input);
//                 if (inputValue.value !== "") {
//                     valueList.push(inputValue.value);
//                 } else {
//                     valueList.push(inputValue.placeholder);
//                 }
//             });
//         } else {
//             const inputList = [
//                 ".small-username-input",
//                 ".small-role-input",
//                 ".small-name-input",
//                 ".small-email-input",
//                 ".small-phone-input",
//                 ".small-gender-input",
//                 ".small-status-input",
//                 ".small-facebook-input",
//                 ".small-birth-input",
//                 ".small-ava-input",
//             ];

//             inputList.forEach((input) => {
//                 var inputValue = document.querySelector(input);
//                 if (inputValue.value !== "") {
//                     valueList.push(inputValue.value);
//                 } else {
//                     valueList.push(inputValue.placeholder);
//                 }
//             });
//         }

//         const titleList = [
//             ".username-title",
//             ".role-title",
//             ".name-title",
//             ".email-title",
//             ".phone-title",
//             ".gender-title",
//             ".facebook-title",
//             ".birth-title",
//             ".small-username-title",
//             ".small-role-title",
//             ".small-name-title",
//             ".small-email-title",
//             ".small-phone-title",
//             ".small-gender-title",
//             ".small-facebook-title",
//             ".small-birth-title",
//         ];

//         titleList.forEach((title) => {
//             var titleName = document.querySelector(title);
//             titleName.style.color = "black";
//         });
//     };

//     const handleSave = (e) => {
//         e.preventDefault();
//         console.log(currentProfile);
//     }

//     const toTitleCase = (phrase) => {
//         return phrase
//             .toLowerCase()
//             .split(" ")
//             .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
//             .join(" ");
//     };

//     const inputFill = (titleName, titleInput) => {
//         var titleValue = document.querySelector(titleName);
//         var inputValue = document.querySelector(titleInput);

//         if (inputValue.value !== "") {
//             if (inputValue.placeholder !== inputValue.value) {
//                 titleValue.style.color = "rgb(242, 82, 82)";
//             } else {
//                 titleValue.style.color = "black";
//             }
//         } else {
//             titleValue.style.color = "black";
//         }
//     };

//     const dropdownClick = (titleName, titleInput, type) => {
//         var titleValue = document.querySelector(titleName);

//         if (type === "gender") {
//             if (
//                 document.querySelector(titleInput).innerHTML !==
//                 (currentProfile.gender === "male" ? "Nam" : "Nữ")
//             ) {
//                 titleValue.style.color = "rgb(242, 82, 82)";
//             } else {
//                 titleValue.style.color = "black";
//             }
//         }
//         if (type === "status") {
//             if (
//                 document.querySelector(titleInput).innerHTML !==
//                 (currentProfile.status === "Active" ? "Hoạt động" : "Bị khóa")
//             ) {
//                 titleValue.style.color = "rgb(242, 82, 82)";
//             } else {
//                 titleValue.style.color = "black";
//             }
//         }
//         if (type === "role") {
//             if (document.querySelector(titleInput).innerHTML !== toTitleCase(currentProfile.role)) {
//                 titleValue.style.color = "rgb(242, 82, 82)";
//             } else {
//                 titleValue.style.color = "black";
//             }
//         }
//     };

//     return (
//         <div class="bg-white font-Kanit" data-theme="retro">
//             <div class="lg:block hidden">
//                 <div class="card lg:card-side bg-base-200 shadow-2xl sm:w-[65%] xl:w-[55%] 2xl:w-[55%] absolute top-[20%] sm:left-[22%] xl:left-[24%] 2xl:left-[24%] z-20">
//                     <figure class="w-1/2">
//                         <img
//                             class="object-cover ava-image cursor-pointer"
//                             alt="Album"
//                             src={currentProfile.avatarUrl ? currentProfile.avatarUrl : avatar}
//                             onClick={() => {
//                                 document.querySelector(".ava-input").click();
//                             }}
//                         />
//                         <input
//                             type="file"
//                             class="ava-input hidden"
//                             accept=".jpg,.jpeg,.png"
//                             onChange={() => {
//                                 var input = document.querySelector(".ava-input");
//                                 var fReader = new FileReader();
//                                 fReader.readAsDataURL(input.files[0]);
//                                 fReader.onloadend = function (event) {
//                                     document.querySelector(".ava-image").src = event.target.result;
//                                 };
//                             }}
//                         />
//                     </figure>
//                     <div class="card-body p-5">
//                         <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
//                             <div class="username-title flex-none">Tên tài khoản:&nbsp;</div>
//                             <input
//                                 type="text"
//                                 name="username"
//                                 onChange={handleChange}
//                                 value={user.username}
//                                 placeholder={currentProfile === null ? "" : currentProfile.username}
//                                 class="username-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
//                                 onKeyUp={() => {
//                                     inputFill(".username-title", ".username-input");
//                                 }}
//                             />
//                         </div>
//                         <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
//                             <div class="role-title flex-none">Vai trò:&nbsp;</div>
//                             <div class="dropdown p-0 h-7 w-full">
//                                 <div
//                                     tabindex="0"
//                                     role="button"
//                                     class="role-input input sm:text-base xl:text-lg 2xl:text-xl font-normal pl-1 h-7"
//                                 >
//                                     {currentProfile === null
//                                         ? ""
//                                         : toTitleCase(currentProfile.role)}
//                                 </div>
//                                 <ul
//                                     tabindex="0"
//                                     class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-1 mt-1 shadow"
//                                 >
//                                     <li>
//                                         <a
//                                             onClick={() => {
//                                                 document.querySelector(".role-input").innerHTML =
//                                                     "Brand";
//                                                 dropdownClick(".role-title", ".role-input", "role");
//                                             }}
//                                         >
//                                             Brand
//                                         </a>
//                                     </li>
//                                     <li>
//                                         <a
//                                             onClick={() => {
//                                                 document.querySelector(".role-input").innerHTML =
//                                                     "Customer";
//                                                 dropdownClick(".role-title", ".role-input", "role");
//                                             }}
//                                         >
//                                             Customer
//                                         </a>
//                                     </li>
//                                     <li>
//                                         <a
//                                             onClick={() => {
//                                                 document.querySelector(".role-input").innerHTML =
//                                                     "Admin";
//                                                 dropdownClick(".role-title", ".role-input", "role");
//                                             }}
//                                         >
//                                             Admin
//                                         </a>
//                                     </li>
//                                 </ul>
//                             </div>
//                         </div>
//                         <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
//                             <div class="name-title flex-none">Họ và tên:&nbsp;</div>
//                             <input
//                                 type="text"
//                                 name="fullName"
//                                 onChange={handleChange}
//                                 value={user.fullName}
//                                 class="name-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
//                                 onKeyUp={() => {
//                                     inputFill(".name-title", ".name-input");
//                                 }}
//                             />
//                         </div>
//                         <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
//                             <div class="email-title flex-none">Email:&nbsp;</div>
//                             <input
//                                 type="text"
//                                 name="email"
//                                 onChange={handleChange}
//                                 value={user.email}
//                                 class="email-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
//                                 onKeyUp={() => {
//                                     inputFill(".email-title", ".email-input");
//                                 }}
//                             />
//                         </div>
//                         <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
//                             <div class="phone-title flex-none">Điện thoại:&nbsp;</div>
//                             <input
//                                 type="text"
//                                 name="phoneNumber"
//                                 onChange={handleChange}
//                                 value={user.phoneNumber}
//                                 class="phone-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
//                                 onKeyUp={() => {
//                                     inputFill(".phone-title", ".phone-input");
//                                 }}
//                             />
//                         </div>
//                         <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
//                             <div class="gender-title flex-none">Giới tính:&nbsp;</div>
//                             <div class="dropdown p-0 h-7 w-full">
//                                 <div
//                                     tabindex="0"
//                                     role="button"
//                                     class="gender-input input sm:text-base xl:text-lg 2xl:text-xl font-normal pl-1 h-7"
//                                 >
//                                     {currentProfile === null
//                                         ? ""
//                                         : currentProfile.gender === "male"
//                                         ? "Nam"
//                                         : (currentProfile.gender === "male" ? "Nữ" : "")}
//                                 </div>
//                                 <ul
//                                     tabindex="0"
//                                     class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-1 mt-1 shadow"
//                                 >
//                                     <li>
//                                         <a
//                                             onClick={() => {
//                                                 document.querySelector(".gender-input").innerHTML =
//                                                     "Nam";
//                                                 dropdownClick(
//                                                     ".gender-title",
//                                                     ".gender-input",
//                                                     "gender"
//                                                 );
//                                             }}
//                                         >
//                                             Nam
//                                         </a>
//                                     </li>
//                                     <li>
//                                         <a
//                                             onClick={() => {
//                                                 document.querySelector(".gender-input").innerHTML =
//                                                     "Nữ";
//                                                 dropdownClick(
//                                                     ".gender-title",
//                                                     ".gender-input",
//                                                     "gender"
//                                                 );
//                                             }}
//                                         >
//                                             Nữ
//                                         </a>
//                                     </li>
//                                 </ul>
//                             </div>
//                         </div>
//                         <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
//                             <div class="status-title flex-none">Trạng thái:&nbsp;</div>
//                             <div class="dropdown p-0 h-7 w-full">
//                                 <div
//                                     tabindex="0"
//                                     role="button"
//                                     class="status-input input sm:text-base xl:text-lg 2xl:text-xl font-normal pl-1 h-7"
//                                 >
//                                     {currentProfile === null
//                                         ? ""
//                                         : currentProfile.status === "ACTIVE"
//                                         ? "Hoạt động"
//                                         : (currentProfile.status === "PENDING" ? "Đang chờ" : "Bị Khóa")}
//                                 </div>
//                                 <ul
//                                     tabindex="0"
//                                     class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-1 mt-1 shadow"
//                                 >
//                                     <li>
//                                         <a
//                                             onClick={() => {
//                                                 document.querySelector(".status-input").innerHTML =
//                                                     "Hoạt động";
//                                                 dropdownClick(
//                                                     ".status-title",
//                                                     ".status-input",
//                                                     "status"
//                                                 );
//                                             }}
//                                         >
//                                             Hoạt động
//                                         </a>
//                                     </li>
//                                     <li>
//                                         <a
//                                             onClick={() => {
//                                                 document.querySelector(".status-input").innerHTML =
//                                                     "Bị khóa";
//                                                 dropdownClick(
//                                                     ".status-title",
//                                                     ".status-input",
//                                                     "status"
//                                                 );
//                                             }}
//                                         >
//                                             Bị khóa
//                                         </a>
//                                     </li>
//                                 </ul>
//                             </div>
//                         </div>
//                         <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
//                             <div class="flex-none facebook-title">Facebook:&nbsp;</div>
//                             <input
//                                 type="text"
//                                 placeholder={
//                                     currentProfile === null ? "" : currentProfile.accountFacebook
//                                 }
//                                 class="facebook-input input w-full sm:text-base xl:text-lg 2xl:text-xl p-0 h-7 placeholder-black"
//                                 onKeyUp={() => {
//                                     inputFill(".facebook-title", ".facebook-input");
//                                 }}
//                             />
//                         </div>
//                         <button
//                             className="save-button btn btn-success brightness-125 w-full"
//                             onClick={handleSave}
//                         >
//                             <svg
//                                 xmlns="http://www.w3.org/2000/svg"
//                                 class="h-6 w-6"
//                                 viewBox="0 0 24 24"
//                                 fill="none"
//                                 stroke="#ffffff"
//                                 stroke-width="2"
//                                 stroke-linecap="round"
//                                 stroke-linejoin="round"
//                             >
//                                 <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
//                                 <polyline points="17 21 17 13 7 13 7 21"></polyline>
//                                 <polyline points="7 3 7 8 15 8"></polyline>
//                             </svg>
//                         </button>
//                     </div>
//                 </div>
//             </div>
//             {/* no need */}
//             <div class="lg:hidden">
//                 <div class="card card-side bg-base-200 shadow-2xl max-w-[60%] h-[60%] overflow-y-scroll no-scrollbar absolute top-[28%] sm:top-[30%] md:top-[30%] left-[20%] z-20">
//                     <div class="card-body p-0">
//                         <img
//                             class="object-fill small-ava-image cursor-pointer"
//                             alt="Album"
//                             src={avatar}
//                             onClick={() => {
//                                 document.querySelector(".small-ava-input").click();
//                             }}
//                         />
//                         <input
//                             type="file"
//                             class="small-ava-input hidden"
//                             accept=".jpg,.jpeg,.png"
//                             onChange={() => {
//                                 var smallInput = document.querySelector(".small-ava-input");
//                                 var smallfReader = new FileReader();
//                                 smallfReader.readAsDataURL(smallInput.files[0]);
//                                 smallfReader.onloadend = function (event) {
//                                     document.querySelector(".small-ava-image").src =
//                                         event.target.result;
//                                 };
//                             }}
//                         />
//                         <div class="p-4 flex flex-col">
//                             <div class="flex text-base sm:text-lg md:text-xl mb-1">
//                                 <div class="small-username-title flex-none sm:text-lg md:text-xl">
//                                     Tên tài khoản:&nbsp;
//                                 </div>
//                                 <input
//                                     type="text"
//                                     placeholder={
//                                         currentProfile === null ? "" : currentProfile.userName
//                                     }
//                                     class="small-username-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
//                                     onKeyUp={() => {
//                                         inputFill(".small-username-title", ".small-username-input");
//                                     }}
//                                 />
//                             </div>
//                             <div class="flex text-base sm:text-lg md:text-xl mb-1">
//                                 <div class="small-role-title sm:text-lg md:text-xl flex-none">
//                                     Vai trò:&nbsp;
//                                 </div>
//                                 <div class="dropdown p-0 h-6 w-full">
//                                     <div
//                                         tabindex="0"
//                                         role="button"
//                                         class="small-role-input input text-base sm:text-lg md:text-xl font-normal pl-1 h-6 mb-1"
//                                     >
//                                         {currentProfile === null
//                                             ? ""
//                                             : toTitleCase(currentProfile.role)}
//                                     </div>
//                                     <ul
//                                         tabindex="0"
//                                         class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-0 shadow"
//                                     >
//                                         <li>
//                                             <a
//                                                 onClick={() => {
//                                                     document.querySelector(
//                                                         ".small-role-input"
//                                                     ).innerHTML = "Brand";
//                                                     dropdownClick(
//                                                         ".small-role-title",
//                                                         ".small-role-input",
//                                                         "role"
//                                                     );
//                                                 }}
//                                             >
//                                                 Brand
//                                             </a>
//                                         </li>
//                                         <li>
//                                             <a
//                                                 onClick={() => {
//                                                     document.querySelector(
//                                                         ".small-role-input"
//                                                     ).innerHTML = "Customer";
//                                                     dropdownClick(
//                                                         ".small-role-title",
//                                                         ".small-role-input",
//                                                         "role"
//                                                     );
//                                                 }}
//                                             >
//                                                 Customer
//                                             </a>
//                                         </li>
//                                         <li>
//                                             <a
//                                                 onClick={() => {
//                                                     document.querySelector(
//                                                         ".small-role-input"
//                                                     ).innerHTML = "Admin";
//                                                     dropdownClick(
//                                                         ".small-role-title",
//                                                         ".small-role-input",
//                                                         "role"
//                                                     );
//                                                 }}
//                                             >
//                                                 Admin
//                                             </a>
//                                         </li>
//                                     </ul>
//                                 </div>
//                             </div>
//                             <div class="flex text-base sm:text-lg md:text-xl mb-1">
//                                 <div class="small-name-title flex-none sm:text-lg md:text-xl">
//                                     Họ và tên:&nbsp;
//                                 </div>
//                                 <input
//                                     type="text"
//                                     placeholder={
//                                         currentProfile === null
//                                             ? ""
//                                             : toTitleCase(currentProfile.fullName)
//                                     }
//                                     class="small-name-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
//                                     onKeyUp={() => {
//                                         inputFill(".small-name-title", ".small-name-input");
//                                     }}
//                                 />
//                             </div>
//                             <div class="flex text-base sm:text-lg md:text-xl mb-1">
//                                 <div class="small-email-title flex-none sm:text-lg md:text-xl">
//                                     Email:&nbsp;
//                                 </div>
//                                 <input
//                                     type="text"
//                                     placeholder={
//                                         currentProfile === null ? "" : currentProfile.email
//                                     }
//                                     class="small-email-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
//                                     onKeyUp={() => {
//                                         inputFill(".small-email-title", ".small-email-input");
//                                     }}
//                                 />
//                             </div>
//                             <div class="flex text-base sm:text-lg md:text-xl mb-1">
//                                 <div class="small-phone-title flex-none sm:text-lg md:text-xl">
//                                     Điện thoại:&nbsp;
//                                 </div>
//                                 <input
//                                     type="text"
//                                     placeholder={
//                                         currentProfile === null ? "" : currentProfile.phoneNumber
//                                     }
//                                     class="small-phone-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
//                                     onKeyUp={() => {
//                                         inputFill(".small-phone-title", ".small-phone-input");
//                                     }}
//                                 />
//                             </div>
//                             <div class="flex text-base sm:text-lg md:text-xl mb-1">
//                                 <div class="small-gender-title sm:text-lg md:text-xl flex-none">
//                                     Giới tính:&nbsp;
//                                 </div>
//                                 <div class="dropdown p-0 h-6 w-full">
//                                     <div
//                                         tabindex="0"
//                                         role="button"
//                                         class="small-gender-input input text-base sm:text-lg md:text-xl font-normal pl-1 h-6 mb-1"
//                                     >
//                                         {currentProfile === null
//                                             ? ""
//                                             : currentProfile.gender === "male"
//                                             ? "Nam"
//                                             : "Nữ"}
//                                     </div>
//                                     <ul
//                                         tabindex="0"
//                                         class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-0 shadow"
//                                     >
//                                         <li>
//                                             <a
//                                                 onClick={() => {
//                                                     document.querySelector(
//                                                         ".small-gender-input"
//                                                     ).innerHTML = "Nam";
//                                                     dropdownClick(
//                                                         ".small-gender-title",
//                                                         ".small-gender-input",
//                                                         "gender"
//                                                     );
//                                                 }}
//                                             >
//                                                 Nam
//                                             </a>
//                                         </li>
//                                         <li>
//                                             <a
//                                                 onClick={() => {
//                                                     document.querySelector(
//                                                         ".small-gender-input"
//                                                     ).innerHTML = "Nữ";
//                                                     dropdownClick(
//                                                         ".small-gender-title",
//                                                         ".small-gender-input",
//                                                         "gender"
//                                                     );
//                                                 }}
//                                             >
//                                                 Nữ
//                                             </a>
//                                         </li>
//                                     </ul>
//                                 </div>
//                             </div>
//                             <div class="flex text-base sm:text-lg md:text-xl mb-1">
//                                 <div class="small-status-title flex-none sm:text-lg md:text-xl">
//                                     Trạng thái:&nbsp;
//                                 </div>
//                                 <div class="dropdown p-0 h-6 w-full">
//                                     <div
//                                         tabindex="0"
//                                         role="button"
//                                         class="small-status-input input text-base sm:text-lg md:text-xl font-normal pl-1 h-6 mb-1"
//                                     >
//                                         {currentProfile === null
//                                             ? ""
//                                             : currentProfile.gender === "Active"
//                                             ? "Hoạt động"
//                                             : "Bị khóa"}
//                                     </div>
//                                     <ul
//                                         tabindex="0"
//                                         class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-0 shadow"
//                                     >
//                                         <li>
//                                             <a
//                                                 onClick={() => {
//                                                     document.querySelector(
//                                                         ".small-status-input"
//                                                     ).innerHTML = "Hoạt động";
//                                                     dropdownClick(
//                                                         ".small-status-title",
//                                                         ".small-status-input",
//                                                         "status"
//                                                     );
//                                                 }}
//                                             >
//                                                 Hoạt động
//                                             </a>
//                                         </li>
//                                         <li>
//                                             <a
//                                                 onClick={() => {
//                                                     document.querySelector(
//                                                         ".small-status-input"
//                                                     ).innerHTML = "Bị khóa";
//                                                     dropdownClick(
//                                                         ".small-status-title",
//                                                         ".small-status-input",
//                                                         "status"
//                                                     );
//                                                 }}
//                                             >
//                                                 Bị khóa
//                                             </a>
//                                         </li>
//                                     </ul>
//                                 </div>
//                             </div>
//                             <div class="flex text-base sm:text-lg md:text-xl mb-1">
//                                 <div class="flex-none small-facebook-title sm:text-lg md:text-xl">
//                                     Facebook:&nbsp;
//                                 </div>
//                                 <input
//                                     type="text"
//                                     placeholder={
//                                         currentProfile === null
//                                             ? ""
//                                             : currentProfile.accountFacebook
//                                     }
//                                     class="small-facebook-input input w-full sm:text-lg md:text-xl pl-1 h-6 placeholder-black"
//                                     onKeyUp={() => {
//                                         inputFill(".small-facebook-title", ".small-facebook-input");
//                                     }}
//                                 />
//                             </div>
//                             <div class="flex text-base sm:text-lg md:text-xl mb-2">
//                                 <div class="small-birth-title flex-none sm:text-lg md:text-xl">
//                                     Ngày sinh:&nbsp;
//                                 </div>
//                                 <input
//                                     type="text"
//                                     placeholder={
//                                         currentProfile === null ? "" : currentProfile.dayOfBirth
//                                     }
//                                     class="small-birth-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
//                                     onKeyUp={() => {
//                                         inputFill(".small-birth-title", ".small-birth-input");
//                                     }}
//                                 />
//                             </div>
//                             <button
//                                 className="small-save-button btn btn-success brightness-125 w-full"
//                                 onClick={() => {
//                                     confirmSave("small");
//                                 }}
//                             >
//                                 <svg
//                                     xmlns="http://www.w3.org/2000/svg"
//                                     class="h-6 w-6"
//                                     viewBox="0 0 24 24"
//                                     fill="none"
//                                     stroke="#ffffff"
//                                     stroke-width="2"
//                                     stroke-linecap="round"
//                                     stroke-linejoin="round"
//                                 >
//                                     <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
//                                     <polyline points="17 21 17 13 7 13 7 21"></polyline>
//                                     <polyline points="7 3 7 8 15 8"></polyline>
//                                 </svg>
//                             </button>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     );
// }

function EditUserForm({ currentProfile, handleClose }) {
    const [user, setUser] = useState(currentProfile );

  const formAccount = useRef(null);


  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser(prev => ({ ...prev, [name]: value }));
  };

  const updateAccountMutation = useMutation(
    ({id,data}) => callApiUpdateAccount(id,data),
    {
      onSuccess: (data) => {
        console.log(data)
        handleClose();
        //handleNoti(false,"Cập nhật thông tin thành công")
        
      },
      onError: (error) => {
          const msgErr = error.response.data.message;
          //setIsError(true);
          //setShowNoti(true);
          //setNotiMsg(msgErr);
      },
    }
  )



  const handleSaveChange = (e) => {
    // handleClose();
    e.preventDefault();
    const formData = new FormData(formAccount.current);
    const formProps = Object.fromEntries(formData);
    console.log(formProps)

    updateAccountMutation.mutate({id: user.idUser,data: formProps});
  };



  return (
    <div data-theme="retro" className="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50 z-50">
      <div className="relative bg-base-100 p-5 rounded-lg shadow-lg w-full max-w-4xl h-auto mx-4">
        <button
          className="absolute top-4 right-4 text-gray-500 hover:text-gray-900"
          onClick={handleClose}
          >
          <IoMdClose size={24} />
        </button>
        <form className="container ml-4 mr-4" ref={formAccount}>
          <div className="flex flex-col gap-4">
            <h3 className="font-bold text-info text-[24px]">Chỉnh sửa tài khoản</h3>
            <div className="w-[150px] h-[150px]">
              <img src={user.avatarUrl || avatar1} alt="avt" className="h-[150px] w-[150px] rounded-full" />
            </div>
            <div className="flex flex-row items-center ">
              <label className="flex flex-col justify-between mr-8 w-full">
                Họ và tên
                <input type="text" name="fullName" value={user.fullName} onChange={handleChange} className="input_text input input-bordered input-info" />
              </label>
              <label className="flex flex-col justify-between mr-8 w-full">
                Username
                <input type="text" name="username" value={user.username} onChange={handleChange} className="input_text input input-bordered input-info" />
              </label>
            </div>
            <div className="flex flex-row items-center">
              <label className="flex flex-col justify-between mr-8 w-full">
                Email
                <input type="email" name="email" value={user.email} onChange={handleChange} className="input_text input input-bordered input-info" />
              </label>
              <label className="flex flex-col justify-between mr-8 w-full">
                Số điện thoại
                <input type="number" name="phoneNumber" value={user.phoneNumber} onChange={handleChange} className="input_text input input-bordered input-info" />
              </label>
            </div>
            <div className="flex flex-row items-center">
              <div className="flex flex-col justify-between mr-8 w-full">
                <label className="flex flex-col justify-between relative">
                  Vai trò
                  <select
                    name="role"
                    value={user.role}
                    onChange={handleChange} 
                    className="input_dropdown input input-bordered input-info">
                    <option value="PLAYER">Player</option>
                    <option value="ADMIN">Admin</option>
                    <option value="BRAND">Brand</option>
                  </select>
                </label>
              </div>

              <div className="flex flex-col justify-between mr-8 w-full">
                <label className="flex flex-col justify-between relative">
                  Trạng thái
                  <select
                    name="status"
                    value={user.status}
                    onChange={handleChange} 
                    className="input_dropdown input input-bordered input-info">
                    <option value="ACTIVE">Active</option>
                    <option value="INACTIVE">Inactive</option>
                    <option value="PENDING">Pending</option>
                  </select>
                </label>
              </div>
            </div>
          </div>
          <button type="submit" className="btn bg-primary rounded-[12px] pt-2 pb-2 pr-3 pl-3 font-bold text-white w-[200px] mt-6" onClick={handleSaveChange}>Chỉnh sửa</button>
        </form>
      </div>
    </div>
  );
}

export default EditUserForm;
