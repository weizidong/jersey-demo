package com.wzd.model.mapper;

import com.wzd.model.entity.Ticket;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface TicketMapper extends Mapper<Ticket>, InsertListMapper<Ticket> {
}