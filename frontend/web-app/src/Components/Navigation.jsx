import React from 'react'
import { Link } from 'react-router-dom'

const Navigation = () => {
  return (
    <nav class="bg-gray-50 dark:bg-gray-700">
        <div class="max-w-screen-xl px-4 py-3 mx-auto">
            <div class="flex items-center">
                <ul class="flex flex-row font-medium mt-0 space-x-8 rtl:space-x-reverse text-sm">
                    <li>
                        <a href="#" class="text-gray-900 dark:text-white hover:underline" aria-current="page"><Link to="/admin">TRANG CHỦ</Link></a>
                    </li>
                    <li>
                        <a href="#" class="text-gray-900 dark:text-white hover:underline"><Link to="/admin/userManagement">QUẢN LÝ NGƯỜI DÙNG</Link></a>
                    </li>
                    <li>
                        <a href="#" class="text-gray-900 dark:text-white hover:underline"><Link to="/admin/gameManagement">QUẢN LÝ TRÒ CHƠI</Link></a>
                    </li>
                    <li>
                        <a href="#" class="text-gray-900 dark:text-white hover:underline"><Link to="/admin/eventManagement">QUẢN LÝ EVENT VÀ VOUCHER</Link></a>
                    </li>
                    <li>
                        <a href="#" class="text-gray-900 dark:text-white hover:underline"><Link to="/admin/statistic">THỐNG KÊ</Link></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

  )
}

export default Navigation