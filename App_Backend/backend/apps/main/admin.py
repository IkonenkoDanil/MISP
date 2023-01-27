from django.contrib import admin
from .models import DayStats, Note


@admin.register(DayStats)
class DayStatsAdmin(admin.ModelAdmin):
    list_display = ('date', 'user', 'text')


@admin.register(Note)
class NoteAdmin(admin.ModelAdmin):
    list_display = ('time', 'day', 'emotion', 'text')
