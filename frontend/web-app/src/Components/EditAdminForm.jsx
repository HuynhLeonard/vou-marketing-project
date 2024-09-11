/* eslint-disable jsx-a11y/anchor-is-valid */
import React from "react";
import avatar from "../utils/images/ava.jpg";

function EditAdminForm() {
    const toTitleCase = (phrase) => {
        return phrase
            .toLowerCase()
            .split(" ")
            .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
            .join(" ");
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

    return (
        <div class="bg-white font-Kanit z-20" data-theme="retro">
            <div class="flex w-full">
                <div class="flex flex-col w-1/3 p-5 avatar">
                    <div class="rounded-full w-full">
                        <img
                            class="object-cover ava-image cursor-pointer"
                            alt="Album"
                            src={avatar}
                            onClick={() => {
                                document.querySelector(".ava-input").click();
                            }}
                        />
                        <input
                            type="file"
                            class="ava-input hidden"
                            accept=".jpg,.jpeg,.png"
                            onChange={() => {
                                var input = document.querySelector(".ava-input");
                                var fReader = new FileReader();
                                fReader.readAsDataURL(input.files[0]);
                                fReader.onloadend = function (event) {
                                    document.querySelector(".ava-image").src = event.target.result;
                                };
                            }}
                        />
                    </div>
                    <div class="p-5 visible lg:invisible lg:h-0">
                        <button
                            className="save-button btn btn-success brightness-125 w-full"
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
                <div class="flex flex-col w-2/3 p-5">
                    <div class="flex w-full">
                        <div class="flex flex-col w-1/2 p-5">
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl mb-3">
                                <div class="username-title flex-none">Tên tài khoản:&nbsp;</div>
                                <input
                                    type="text"
                                    class="username-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".username-title", ".username-input");
                                    }}
                                />
                            </div>
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl mb-3">
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
                                                    document.querySelector(
                                                        ".role-input"
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
                                                        ".role-input"
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
                                                        ".role-input"
                                                    ).innerHTML = "Admin";
                                                }}
                                            >
                                                Admin
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl mb-3">
                                <div class="name-title flex-none">Họ và tên:&nbsp;</div>
                                <input
                                    type="text"
                                    class="name-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".name-title", ".name-input");
                                    }}
                                />
                            </div>
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl mb-3">
                                <div class="email-title flex-none">Email:&nbsp;</div>
                                <input
                                    type="text"
                                    class="email-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".email-title", ".email-input");
                                    }}
                                />
                            </div>
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl">
                                <div class="phone-title flex-none">Điện thoại:&nbsp;</div>
                                <input
                                    type="text"
                                    class="phone-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".phone-title", ".phone-input");
                                    }}
                                />
                            </div>
                        </div>
                        <div class="flex flex-col w-1/2 p-5">
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl mb-3">
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
                                                    document.querySelector(
                                                        ".gender-input"
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
                                                        ".gender-input"
                                                    ).innerHTML = "Nữ";
                                                }}
                                            >
                                                Nữ
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl mb-3">
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
                                                    document.querySelector(
                                                        ".status-input"
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
                                                        ".status-input"
                                                    ).innerHTML = "Bị khóa";
                                                }}
                                            >
                                                Bị khóa
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl mb-3 h-[40%]">
                                <div class="flex-none facebook-title">Facebook:&nbsp;</div>
                                <textarea
                                    type="text"
                                    class="facebook-input input w-full sm:text-base xl:text-lg 2xl:text-xl p-0 h-[100%] placeholder-black"
                                    onKeyUp={() => {
                                        inputFill(".facebook-title", ".facebook-input");
                                    }}
                                />
                            </div>
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl">
                                <div class="birth-title flex-none">Ngày sinh:&nbsp;</div>
                                <input
                                    type="text"
                                    class="birth-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap p-0 h-7 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".birth-title", ".birth-input");
                                    }}
                                />
                            </div>
                        </div>
                    </div>
                    <div class="p-5 mt-5 invisible lg:visible">
                        <button
                            className="save-button btn btn-success brightness-125 w-full"
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
        </div>
    );
}

export default EditAdminForm;
