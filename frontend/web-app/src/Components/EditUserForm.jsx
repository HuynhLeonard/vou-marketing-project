/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import avatar from "../utils/images/ava.jpg";

function EditUserForm() {
    const confirmSave = (buttonSize) => {
        var valueList = [];

        if (buttonSize === "large") {
            const inputList = [
                ".username-input",
                ".role-input",
                ".name-input",
                ".email-input",
                ".phone-input",
                ".gender-input",
                ".status-input",
                ".facebook-input",
                ".birth-input",
                ".ava-input",
            ];

            inputList.forEach((input) => {
                var inputValue = document.querySelector(input);
                if (inputValue.value !== "") {
                    valueList.push(inputValue.value);
                } else {
                    valueList.push(inputValue.placeholder);
                }
            });
        } else {
            const inputList = [
                ".small-username-input",
                ".small-role-input",
                ".small-name-input",
                ".small-email-input",
                ".small-phone-input",
                ".small-gender-input",
                ".small-status-input",
                ".small-facebook-input",
                ".small-birth-input",
                ".small-ava-input",
            ];

            inputList.forEach((input) => {
                var inputValue = document.querySelector(input);
                if (inputValue.value !== "") {
                    valueList.push(inputValue.value);
                } else {
                    valueList.push(inputValue.placeholder);
                }
            });
        }

        const titleList = [
            ".username-title",
            ".role-title",
            ".name-title",
            ".email-title",
            ".phone-title",
            ".gender-title",
            ".facebook-title",
            ".birth-title",
            ".small-username-title",
            ".small-role-title",
            ".small-name-title",
            ".small-email-title",
            ".small-phone-title",
            ".small-gender-title",
            ".small-facebook-title",
            ".small-birth-title",
        ];

        titleList.forEach((title) => {
            var titleName = document.querySelector(title);
            titleName.style.color = "black";
        });
    };

    const inputFill = (titleName, titleInput) => {
        if (titleName !== "") {
            var titleValue = document.querySelector(titleName);
            var inputValue = document.querySelector(titleInput);

            if (inputValue.value !== "") {
                if (inputValue.placeholder !== inputValue.value) {
                    titleValue.style.color = "rgb(242, 82, 82)";
                } else {
                    titleValue.style.color = "black";
                }
            } else {
                titleValue.style.color = "black";
            }
        }
    };

    function loadXHR(url) {
        return new Promise(function (resolve, reject) {
            try {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", url);
                xhr.responseType = "blob";
                xhr.onerror = function () {
                    reject("Network error.");
                };
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        resolve(xhr.response);
                    } else {
                        reject("Loading error:" + xhr.statusText);
                    }
                };
                xhr.send();
            } catch (err) {
                reject(err.message);
            }
        });
    }

    useEffect(() => {
        // const inputList = [
        //     ".username-input",
        //     ".name-input",
        //     ".email-input",
        //     ".phone-input",
        //     ".facebook-input",
        //     ".birth-input",
        //     ".ava-input",
        // ];
        // var count = 0;
        // inputList.forEach((input) => {
        //     var inputValue = document.querySelector(input);
        //     if (inputValue.value !== "") {
        //         if (inputValue.placeholder !== inputValue.value) {
        //             count += 1;
        //         }
        //         if (input === ".ava-input") {
        //             if (inputValue.value !== currentProfile.avatar) {
        //                 var fReader = new FileReader();
        //                 loadXHR("https://cdn-icons-png.flaticon.com/512/4383/4383956.png").then(
        //                     function (blob) {
        //                         fReader.readAsDataURL(blob);
        //                         fReader.onloadend = function (event) {
        //                             if (
        //                                 document.querySelector(".ava-image").src !==
        //                                 event.target.result
        //                             ) {
        //                                 count += 1;
        //                             }
        //                         };
        //                     }
        //                 );
        //             }
        //         }
        //     }
        // });
        // var genderInput = document.querySelector(".gender-input");
        // if (genderInput.innerHTML !== (currentProfile.gender === "male" ? "Nam" : "Nữ")) {
        //     document.querySelector(".gender-title").style.color = "rgb(242, 82, 82)";
        //     count += 1;
        // } else {
        //     document.querySelector(".gender-title").style.color = "black";
        // }
        // var statusInput = document.querySelector(".status-input");
        // if (
        //     statusInput.innerHTML !== (currentProfile.status === "Active" ? "Hoạt động" : "Bị khóa")
        // ) {
        //     document.querySelector(".status-title").style.color = "rgb(242, 82, 82)";
        //     count += 1;
        // } else {
        //     document.querySelector(".status-title").style.color = "black";
        // }
        // var roleInput = document.querySelector(".role-input");
        // if (roleInput.innerHTML.toLowerCase() !== currentProfile.role) {
        //     document.querySelector(".role-title").style.color = "rgb(242, 82, 82)";
        //     count += 1;
        // } else {
        //     document.querySelector(".role-title").style.color = "black";
        // }
        // var saveButton = document.querySelector(".save-button");
        // if (count > 0) {
        //     saveButton.classList.remove("btn-disabled");
        // } else {
        //     saveButton.classList.add("btn-disabled");
        // }
        // const smallInputList = [
        //     ".small-username-input",
        //     ".small-name-input",
        //     ".small-email-input",
        //     ".small-phone-input",
        //     ".small-facebook-input",
        //     ".small-birth-input",
        //     ".small-ava-input",
        // ];
        // var smallCount = 0;
        // smallInputList.forEach((input) => {
        //     var inputValue = document.querySelector(input);
        //     if (inputValue.value !== "") {
        //         if (inputValue.placeholder !== inputValue.value) {
        //             smallCount += 1;
        //         }
        //         if (input === ".small-ava-input") {
        //             if (inputValue.value !== currentProfile.avatar) {
        //                 var fReader = new FileReader();
        //                 loadXHR("https://cdn-icons-png.flaticon.com/512/4383/4383956.png").then(
        //                     function (blob) {
        //                         fReader.readAsDataURL(blob);
        //                         fReader.onloadend = function (event) {
        //                             if (
        //                                 document.querySelector(".small-ava-image").src !==
        //                                 event.target.result
        //                             ) {
        //                                 count += 1;
        //                             }
        //                         };
        //                     }
        //                 );
        //             }
        //         }
        //     }
        // });
        // var smallGenderInput = document.querySelector(".small-gender-input");
        // if (smallGenderInput.innerHTML !== (currentProfile.gender === "male" ? "Nam" : "Nữ")) {
        //     document.querySelector(".small-gender-title").style.color = "rgb(242, 82, 82)";
        //     smallCount += 1;
        // } else {
        //     document.querySelector(".small-gender-title").style.color = "black";
        // }
        // var smallStatusInput = document.querySelector(".small-status-input");
        // if (
        //     smallStatusInput.innerHTML !==
        //     (currentProfile.gender === "Active" ? "Hoạt động" : "Bị khóa")
        // ) {
        //     document.querySelector(".small-status-title").style.color = "rgb(242, 82, 82)";
        //     smallCount += 1;
        // } else {
        //     document.querySelector(".small-status-title").style.color = "black";
        // }
        // var smallRoleInput = document.querySelector(".small-role-input");
        // if (smallRoleInput.innerHTML.toLowerCase() !== currentProfile.role) {
        //     document.querySelector(".small-role-title").style.color = "rgb(242, 82, 82)";
        //     smallCount += 1;
        // } else {
        //     document.querySelector(".small-role-title").style.color = "black";
        // }
        // var smallSaveButton = document.querySelector(".small-save-button");
        // if (smallCount > 0) {
        //     smallSaveButton.classList.remove("btn-disabled");
        // } else {
        //     smallSaveButton.classList.add("btn-disabled");
        // }
        // if (document.querySelector(".ava-input").value !== "") {
        //     var input = document.querySelector(".ava-input");
        //     var fReader = new FileReader();
        //     fReader.readAsDataURL(input.files[0]);
        //     fReader.onloadend = function (event) {
        //         document.querySelector(".ava-image").src = event.target.result;
        //     };
        // }
        // if (document.querySelector(".small-ava-input").value !== "") {
        //     var smallInput = document.querySelector(".small-ava-input");
        //     var smallfReader = new FileReader();
        //     smallfReader.readAsDataURL(smallInput.files[0]);
        //     smallfReader.onloadend = function (event) {
        //         document.querySelector(".small-ava-image").src = event.target.result;
        //     };
        // }
    }, []);

    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <Link
                    to="/admin/userManagement"
                    class="backdrop-blur-sm w-full h-full absolute top-0 z-20 cursor-pointer"
                ></Link>
                <div class="card lg:card-side bg-base-200 shadow-2xl sm:w-[65%] xl:w-[55%] 2xl:w-[55%] absolute top-[20%] sm:left-[22%] xl:left-[24%] 2xl:left-[24%] z-20">
                    <figure class="w-1/2">
                        <img
                            class="object-cover ava-image cursor-pointer"
                            alt="Album"
                            src={avatar}
                            onClick={() => {
                                document.querySelector(".ava-input").click();
                            }}
                        />
                        <input type="file" class="ava-input hidden" accept=".jpg,.jpeg,.png" />
                    </figure>
                    <div class="card-body p-5">
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="username-title flex-none">Tên tài khoản:&nbsp;</div>
                            <input
                                type="text"
                                class="username-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".username-title", ".username-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="role-title flex-none">Vai trò:&nbsp;</div>
                            <div class="dropdown p-0 h-7 w-full">
                                <div
                                    tabindex="0"
                                    role="button"
                                    class="role-input input sm:text-base xl:text-lg 2xl:text-xl font-normal pl-1 h-7"
                                ></div>
                                <ul
                                    tabindex="0"
                                    class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-1 mt-1 shadow"
                                >
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".role-input").innerHTML =
                                                    "Brand";
                                            }}
                                        >
                                            Brand
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".role-input").innerHTML =
                                                    "Customer";
                                            }}
                                        >
                                            Customer
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".role-input").innerHTML =
                                                    "Admin";
                                            }}
                                        >
                                            Admin
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="name-title flex-none">Họ và tên:&nbsp;</div>
                            <input
                                type="text"
                                class="name-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".name-title", ".name-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="email-title flex-none">Email:&nbsp;</div>
                            <input
                                type="text"
                                class="email-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".email-title", ".email-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="phone-title flex-none">Điện thoại:&nbsp;</div>
                            <input
                                type="text"
                                class="phone-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".phone-title", ".phone-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="gender-title flex-none">Giới tính:&nbsp;</div>
                            <div class="dropdown p-0 h-7 w-full">
                                <div
                                    tabindex="0"
                                    role="button"
                                    class="gender-input input sm:text-base xl:text-lg 2xl:text-xl font-normal pl-1 h-7"
                                ></div>
                                <ul
                                    tabindex="0"
                                    class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-1 mt-1 shadow"
                                >
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".gender-input").innerHTML =
                                                    "Nam";
                                            }}
                                        >
                                            Nam
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".gender-input").innerHTML =
                                                    "Nữ";
                                            }}
                                        >
                                            Nữ
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="status-title flex-none">Trạng thái:&nbsp;</div>
                            <div class="dropdown p-0 h-7 w-full">
                                <div
                                    tabindex="0"
                                    role="button"
                                    class="status-input input sm:text-base xl:text-lg 2xl:text-xl font-normal pl-1 h-7"
                                ></div>
                                <ul
                                    tabindex="0"
                                    class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-1 mt-1 shadow"
                                >
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".status-input").innerHTML =
                                                    "Hoạt động";
                                            }}
                                        >
                                            Hoạt động
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".status-input").innerHTML =
                                                    "Bị khóa";
                                            }}
                                        >
                                            Bị khóa
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="flex-none facebook-title">Facebook:&nbsp;</div>
                            <input
                                type="text"
                                class="facebook-input input w-full sm:text-base xl:text-lg 2xl:text-xl p-0 h-7 placeholder-black"
                                onKeyUp={() => {
                                    inputFill(".facebook-title", ".facebook-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl mb-2">
                            <div class="birth-title flex-none">Ngày sinh:&nbsp;</div>
                            <input
                                type="text"
                                class="birth-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap p-0 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".birth-title", ".birth-input");
                                }}
                            />
                        </div>
                        <button
                            className="save-button btn btn-success btn-disabled brightness-125 w-full"
                            onClick={() => {
                                confirmSave("large");
                            }}
                        >
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                class="h-6 w-6"
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="#ffffff"
                                stroke-width="2"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                            >
                                <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
                                <polyline points="17 21 17 13 7 13 7 21"></polyline>
                                <polyline points="7 3 7 8 15 8"></polyline>
                            </svg>
                        </button>
                    </div>
                </div>
            </div>
            <div class="lg:hidden">
                <Link
                    to="/admin/userManagement"
                    class="backdrop-blur-sm w-full h-[1000px] absolute top-0 z-20 cursor-pointer"
                ></Link>
                <div class="card card-side bg-base-200 shadow-2xl max-w-[60%] h-[60%] overflow-y-scroll no-scrollbar absolute top-[28%] sm:top-[30%] md:top-[30%] left-[20%] z-20">
                    <div class="card-body p-0">
                        <img
                            class="object-fill small-ava-image cursor-pointer"
                            alt="Album"
                            src={avatar}
                            onClick={() => {
                                document.querySelector(".small-ava-input").click();
                            }}
                        />
                        <input
                            type="file"
                            class="small-ava-input hidden"
                            accept=".jpg,.jpeg,.png"
                        />
                        <div class="p-4 flex flex-col">
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-username-title flex-none sm:text-lg md:text-xl">
                                    Tên tài khoản:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-username-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-username-title", ".small-username-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-role-title sm:text-lg md:text-xl flex-none">
                                    Vai trò:&nbsp;
                                </div>
                                <div class="dropdown p-0 h-6 w-full">
                                    <div
                                        tabindex="0"
                                        role="button"
                                        class="small-role-input input text-base sm:text-lg md:text-xl font-normal pl-1 h-6 mb-1"
                                    ></div>
                                    <ul
                                        tabindex="0"
                                        class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-0 shadow"
                                    >
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-role-input"
                                                    ).innerHTML = "Brand";
                                                }}
                                            >
                                                Brand
                                            </a>
                                        </li>
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-role-input"
                                                    ).innerHTML = "Customer";
                                                }}
                                            >
                                                Customer
                                            </a>
                                        </li>
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-role-input"
                                                    ).innerHTML = "Admin";
                                                }}
                                            >
                                                Admin
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-name-title flex-none sm:text-lg md:text-xl">
                                    Họ và tên:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-name-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-name-title", ".small-name-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-email-title flex-none sm:text-lg md:text-xl">
                                    Email:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-email-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-email-title", ".small-email-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-phone-title flex-none sm:text-lg md:text-xl">
                                    Điện thoại:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-phone-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-phone-title", ".small-phone-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-gender-title sm:text-lg md:text-xl flex-none">
                                    Giới tính:&nbsp;
                                </div>
                                <div class="dropdown p-0 h-6 w-full">
                                    <div
                                        tabindex="0"
                                        role="button"
                                        class="small-gender-input input text-base sm:text-lg md:text-xl font-normal pl-1 h-6 mb-1"
                                    ></div>
                                    <ul
                                        tabindex="0"
                                        class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-0 shadow"
                                    >
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-gender-input"
                                                    ).innerHTML = "Nam";
                                                }}
                                            >
                                                Nam
                                            </a>
                                        </li>
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-gender-input"
                                                    ).innerHTML = "Nữ";
                                                }}
                                            >
                                                Nữ
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-status-title flex-none sm:text-lg md:text-xl">
                                    Trạng thái:&nbsp;
                                </div>
                                <div class="dropdown p-0 h-6 w-full">
                                    <div
                                        tabindex="0"
                                        role="button"
                                        class="small-status-input input text-base sm:text-lg md:text-xl font-normal pl-1 h-6 mb-1"
                                    ></div>
                                    <ul
                                        tabindex="0"
                                        class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-0 shadow"
                                    >
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-status-input"
                                                    ).innerHTML = "Hoạt động";
                                                }}
                                            >
                                                Hoạt động
                                            </a>
                                        </li>
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-status-input"
                                                    ).innerHTML = "Bị khóa";
                                                }}
                                            >
                                                Bị khóa
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="flex-none small-facebook-title sm:text-lg md:text-xl">
                                    Facebook:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-facebook-input input w-full sm:text-lg md:text-xl pl-1 h-6 placeholder-black"
                                    onKeyUp={() => {
                                        inputFill(".small-facebook-title", ".small-facebook-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-2">
                                <div class="small-birth-title flex-none sm:text-lg md:text-xl">
                                    Ngày sinh:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-birth-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-birth-title", ".small-birth-input");
                                    }}
                                />
                            </div>
                            <button
                                className="small-save-button btn btn-success btn-disabled brightness-125 w-full"
                                onClick={() => {
                                    confirmSave("small");
                                }}
                            >
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    class="h-6 w-6"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="#ffffff"
                                    stroke-width="2"
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                >
                                    <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
                                    <polyline points="17 21 17 13 7 13 7 21"></polyline>
                                    <polyline points="7 3 7 8 15 8"></polyline>
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default EditUserForm;
