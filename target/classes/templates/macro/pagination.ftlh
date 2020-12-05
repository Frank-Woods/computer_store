<#macro template page url>
    <div class="row">
        <div class="col-sm-12 col-md-5">
            <div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">Показано от ${page.number * page.size + 1} до ${page.number * page.numberOfElements + page.numberOfElements} из ${page.totalElements}</div>
        </div>
        <div class="col-sm-12 col-md-7">
            <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                <ul class="pagination">
                    <li class="paginate_button page-item previous <#if page.pageable.getPageNumber() == 0>disabled</#if>">
                        <form action="${url}" method="get" class="m-0">
                            <input type="hidden" name="page" value="${page.pageable.getPageNumber() - 1}">
                            <button class="page-link" <#if page.pageable.getPageNumber() == 0>disabled</#if>>Предыдущая</button>
                        </form>
                    </li>
                    <#assign lastPage = page.totalPages - 1>
                    <#assign visiblePages = 7>
                    <#assign currentPage = page.pageable.getPageNumber()>
                    <#assign deltaLast = lastPage - currentPage>
                    <#if lastPage <= visiblePages>
                        <#list 0..lastPage as index>
                            <#if index < 0><#break></#if>
                            <li class="paginate_button page-item <#if currentPage == index>active</#if>">
                                <form action="${url}" method="get" class="m-0">
                                    <input type="hidden" name="page" value="${index}">
                                    <button class="page-link" <#if currentPage == index>disabled</#if>>${index + 1}</button>
                                </form>
                            </li>
                        </#list>
                    <#else>
                        <li class="paginate_button page-item <#if currentPage == 0>active</#if>">
                            <form action="${url}" method="get" class="m-0">
                                <input type="hidden" name="page" value="0">
                                <button class="page-link" <#if currentPage == 0>disabled</#if>>1</button>
                            </form>
                        </li>
                        <#if currentPage < (visiblePages / 2)?floor>
                            <#list 1..currentPage + visiblePages - currentPage - 2 as index>
                                <li class="paginate_button page-item <#if currentPage == index>active</#if>">
                                    <form action="${url}" method="get" class="m-0">
                                        <input type="hidden" name="page" value="${index}">
                                        <button class="page-link" <#if currentPage == index>disabled</#if>>${index + 1}</button>
                                    </form>
                                </li>
                            </#list>
                        <#elseif deltaLast < (visiblePages / 2)?floor>
                            <#list currentPage - visiblePages + deltaLast + 2..lastPage - 1 as index>
                                <li class="paginate_button page-item <#if currentPage == index>active</#if>">
                                    <form action="${url}" method="get" class="m-0">
                                        <input type="hidden" name="page" value="${index}">
                                        <button class="page-link" <#if currentPage == index>disabled</#if>>${index + 1}</button>
                                    </form>
                                </li>
                            </#list>
                        <#else>
                            <#list currentPage - (visiblePages / 2)?floor + 1..currentPage + (visiblePages / 2)?floor - 1 as index>
                                <#if index < 1><#continue></#if>
                                <#if lastPage - 1 <= index><#break></#if>
                                <li class="paginate_button page-item <#if currentPage == index>active</#if>">
                                    <form action="${url}" method="get" class="m-0">
                                        <input type="hidden" name="page" value="${index}">
                                        <button class="page-link" <#if currentPage == index>disabled</#if>>${index + 1}</button>
                                    </form>
                                </li>
                            </#list>
                        </#if>
                        <#if lastPage != 0>
                            <li class="paginate_button page-item <#if currentPage == lastPage>active</#if>">
                                <form action="${url}" method="get" class="m-0">
                                    <input type="hidden" name="page" value="${lastPage}">
                                    <button class="page-link" <#if currentPage == lastPage>disabled</#if>>${lastPage + 1}</button>
                                </form>
                            </li>
                        </#if>
                    </#if>
                    <li class="paginate_button page-item <#if page.totalPages <= page.pageable.getPageNumber() + 1>disabled</#if>">
                        <form action="${url}" method="get" class="m-0">
                            <input type="hidden" name="page" value="${page.pageable.getPageNumber() + 1}">
                            <button class="page-link" <#if page.totalPages <= page.pageable.getPageNumber() + 1>disabled</#if>>Следующая</button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</#macro>