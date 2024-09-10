/* eslint-disable jsx-a11y/anchor-is-valid */
import React from "react";

function AddNewUserForm() {
    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <div class="card lg:card-side bg-base-200 shadow-2xl sm:w-[65%] xl:w-[55%] 2xl:w-[55%] absolute top-[20%] sm:left-[22%] xl:left-[24%] 2xl:left-[24%] z-20">
                    <figure class="w-1/2">
                        <img
                            class="object-cover add-ava-image hidden cursor-pointer"
                            alt="Album"
                            onClick={() => {
                                document.querySelector(".add-ava-input").click();
                            }}
                        />
                        <button
                            class="add-ava-button btn btn-circle border-black w-1/2 h-1/2"
                            onClick={() => {
                                document.querySelector(".add-ava-input").click();
                            }}
                        >
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                class="h-10 w-10"
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="#000000"
                                stroke-width="2"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                            >
                                <rect x="3" y="3" width="18" height="18" rx="2" />
                                <circle cx="8.5" cy="8.5" r="1.5" />
                                <path d="M20.4 14.5L16 10 4 20" />
                            </svg>
                            <input
                                type="file"
                                class="add-ava-input hidden"
                                accept=".jpg,.jpeg,.png"
                                onChange={() => {
                                    var input = document.querySelector(".add-ava-input");
                                    var fReader = new FileReader();
                                    fReader.readAsDataURL(input.files[0]);
                                    fReader.onloadend = function (event) {
                                        document.querySelector(".add-ava-image").src =
                                            event.target.result;
                                    };

                                    document
                                        .querySelector(".add-ava-image")
                                        .classList.remove("hidden");
                                    document
                                        .querySelector(".add-ava-button")
                                        .classList.add("hidden");
                                }}
                            />
                        </button>
                    </figure>
                    <div class="card-body p-5">
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="username-title flex-none">Tên tài khoản:&nbsp;</div>
                            <input
                                type="text"
                                class="username-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
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
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="email-title flex-none">Email:&nbsp;</div>
                            <input
                                type="text"
                                class="email-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="phone-title flex-none">Điện thoại:&nbsp;</div>
                            <input
                                type="text"
                                class="phone-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
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
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl mb-2">
                            <div class="birth-title flex-none">Ngày sinh:&nbsp;</div>
                            <input
                                type="text"
                                class="birth-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap p-0 h-7 placeholder-black w-full"
                            />
                        </div>
                        <button
                            className="save-button btn btn-success brightness-125 w-full"
                            onClick={() => {
                                //confirmSave("large");
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
                <div class="card card-side bg-base-200 shadow-2xl max-w-[60%] h-[60%] overflow-y-scroll no-scrollbar absolute top-[28%] sm:top-[30%] md:top-[30%] left-[20%] z-20">
                    <div class="card-body p-0">
                        <figure class="w-full">
                            <img
                                class="object-cover small-add-ava-image hidden cursor-pointer"
                                alt="Album"
                                onClick={() => {
                                    document.querySelector(".small-add-ava-input").click();
                                }}
                            />
                            <button
                                class="small-add-ava-button btn btn-circle border-black mt-3"
                                onClick={() => {
                                    document.querySelector(".small-add-ava-input").click();
                                }}
                            >
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    class="h-8 w-8"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="#000000"
                                    stroke-width="2"
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                >
                                    <rect x="3" y="3" width="18" height="18" rx="2" />
                                    <circle cx="8.5" cy="8.5" r="1.5" />
                                    <path d="M20.4 14.5L16 10 4 20" />
                                </svg>
                                <input
                                    type="file"
                                    class="small-add-ava-input hidden"
                                    accept=".jpg,.jpeg,.png"
                                    onChange={() => {
                                        var smallInput =
                                            document.querySelector(".small-add-ava-input");
                                        var smallfReader = new FileReader();
                                        smallfReader.readAsDataURL(smallInput.files[0]);
                                        smallfReader.onloadend = function (event) {
                                            document.querySelector(".small-add-ava-image").src =
                                                event.target.result;
                                        };

                                        document
                                            .querySelector(".small-add-ava-image")
                                            .classList.remove("hidden");
                                        document
                                            .querySelector(".small-add-ava-button")
                                            .classList.add("hidden");
                                    }}
                                />
                            </button>
                        </figure>
                        <div class="p-4 flex flex-col">
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-username-title flex-none sm:text-lg md:text-xl">
                                    Tên tài khoản:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-username-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
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
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-email-title flex-none sm:text-lg md:text-xl">
                                    Email:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-email-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-phone-title flex-none sm:text-lg md:text-xl">
                                    Điện thoại:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-phone-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
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
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-2">
                                <div class="small-birth-title flex-none sm:text-lg md:text-xl">
                                    Ngày sinh:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    class="small-birth-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                />
                            </div>
                            <button
                                className="small-save-button btn btn-success brightness-125 w-full"
                                onClick={() => {
                                    //confirmSave("small");
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

export default AddNewUserForm;
